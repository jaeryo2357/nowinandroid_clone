package com.example.nowinandroid_clone.feature.foryou

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshotFlow
import androidx.compose.runtime.snapshots.Snapshot.Companion.withMutableSnapshot
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nowinandroid_clone.core.domain.repository.NewsRepository
import com.example.nowinandroid_clone.core.domain.repository.TopicsRepository
import com.example.nowinandroid_clone.core.model.data.FollowableTopic
import com.example.nowinandroid_clone.core.model.data.NewsResource
import com.example.nowinandroid_clone.core.model.data.SaveableNewsResource
import com.example.nowinandroid_clone.ui.saveable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForYouViewModel @Inject constructor(
    private val topicsRepository: TopicsRepository,
    private val newsRepository: NewsRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val followedTopicsStateFlow =
        topicsRepository.getFollowedTopicIdsStream()
            .map { followedTopics ->
                if (followedTopics.isEmpty()) {
                    FollowedTopicsState.None
                } else {
                    FollowedTopicsState.FollowedTopics(
                        topicIds = followedTopics
                    )
                }
            }
            .stateIn(
                viewModelScope,
                started =
                SharingStarted.WhileSubscribed(5_000),
                initialValue = FollowedTopicsState.Unknown
            )

    private var inProgressTopicSelection by savedStateHandle.saveable {
        mutableStateOf<Set<Int>>(emptySet())
    }

    private var savedNewsResources by savedStateHandle.saveable {
        mutableStateOf<Set<Int>>(emptySet())
    }

    val uiState: StateFlow<ForYouFeedUiState> =
        combine(
            followedTopicsStateFlow,
            topicsRepository.getTopicsStream(),
            snapshotFlow { inProgressTopicSelection },
        ) { followedTopicsUserState, availableTopics, inProgressTopicSelection ->

            fun mapToSaveableFeed(feed: List<NewsResource>): List<SaveableNewsResource> =
                feed.map { newsResource ->
                    SaveableNewsResource(
                        newsResource = newsResource,
                        isSaved = savedNewsResources.contains(newsResource.id)
                    )
                }

            when (followedTopicsUserState) {
                // If we don't know the current selection state, just emit loading.
                FollowedTopicsState.Unknown -> flowOf<ForYouFeedUiState>(ForYouFeedUiState.Loading)
                // If the user has followed topics, use those followed topics to populate the feed
                is FollowedTopicsState.FollowedTopics -> {
                    newsRepository.getNewsResourcesStream(
                        filterTopicIds = followedTopicsUserState.topicIds
                    )
                        .map(::mapToSaveableFeed)
                        .map { feed ->
                            ForYouFeedUiState.PopulatedFeed.FeedWithoutTopicSelection(
                                feed = feed
                            )
                        }
                }
                // If the user hasn't followed topics yet, show the topic selection, as well as a
                // realtime populated feed based on those in-progress topic selections.
                FollowedTopicsState.None -> {
                    newsRepository.getNewsResourcesStream(
                        filterTopicIds = inProgressTopicSelection
                    )
                        .map(::mapToSaveableFeed)
                        .map { feed ->
                            ForYouFeedUiState.PopulatedFeed.FeedWithTopicSelection(
                                topics = availableTopics.map { topic ->
                                   FollowableTopic(
                                       topic = topic,
                                       isFollowed = topic.id in inProgressTopicSelection
                                   )
                                },
                                feed = feed
                            )
                        }
                }
            }
        }.flatMapConcat { it }
            .stateIn(
                viewModelScope,
                started = SharingStarted.Eagerly,
                initialValue = ForYouFeedUiState.Loading
            )

    fun updateTopicSelection(topicId: Int, isChecked: Boolean) {
        withMutableSnapshot {
            inProgressTopicSelection =
                    // Update the in-progress selection based on whether the topic id was checked
                if (isChecked) {
                    inProgressTopicSelection + topicId
                } else {
                    inProgressTopicSelection - topicId
                }
        }
    }

    fun updateNewsResourceSaved(newsResourceId: Int, isChecked: Boolean) {
        withMutableSnapshot {
            savedNewsResources =
                if (isChecked) {
                    savedNewsResources + newsResourceId
                } else {
                    savedNewsResources - newsResourceId
                }
        }
    }

    fun saveFollowedTopics() {
        if (inProgressTopicSelection.isEmpty()) return

        viewModelScope.launch {
            topicsRepository.setFollowedTopicIds(inProgressTopicSelection)
        }
    }

}

sealed interface FollowedTopicsState {
    object Unknown : FollowedTopicsState

    object None : FollowedTopicsState

    data class FollowedTopics(
        val topicIds: Set<Int>
    ) : FollowedTopicsState
}

sealed interface ForYouFeedUiState {
    object Loading : ForYouFeedUiState

    sealed interface PopulatedFeed : ForYouFeedUiState {
        val feed: List<SaveableNewsResource>

        data class FeedWithTopicSelection(
            val topics: List<FollowableTopic>,
            override val feed: List<SaveableNewsResource>
        ) : PopulatedFeed {
            val canSaveSelectedTopics: Boolean =
                topics.any { it.isFollowed }
        }

        data class FeedWithoutTopicSelection(
            override val feed: List<SaveableNewsResource>
        ) : PopulatedFeed
    }
}