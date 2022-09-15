package com.example.nowinandroid_clone.ui.foryou

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshotFlow
import androidx.compose.runtime.snapshots.Snapshot.Companion.withMutableSnapshot
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nowinandroid_clone.data.repository.NewsRepository
import com.example.nowinandroid_clone.data.model.NewsResource
import com.example.nowinandroid_clone.data.model.Topic
import com.example.nowinandroid_clone.data.repository.TopicsRepository
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
                        topics = followedTopics
                    )
                }
            }
            .stateIn(
                viewModelScope,
                started =
                SharingStarted.WhileSubscribed(5_000),
                initialValue = FollowedTopicsState.Unknown
            )

    private var inProgressTopicSelection by
    savedStateHandle.saveable {
        mutableStateOf<Set<Int>>(emptySet())
    }

    val uiState: StateFlow<ForYouFeedUiState> =
        combine(
            followedTopicsStateFlow,
            topicsRepository.getTopicsStream(),
            snapshotFlow { inProgressTopicSelection },
        ) { followedTopicsUserState, availableTopics, inProgressTopicSelection ->
            when (followedTopicsUserState) {
                // If we don't know the current selection state, just emit loading.
                FollowedTopicsState.Unknown -> flowOf<ForYouFeedUiState>(ForYouFeedUiState.Loading)
                // If the user has followed topics, use those followed topics to populate the feed
                is FollowedTopicsState.FollowedTopics -> {
                    newsRepository.getNewsResourcesStream(
                        filterTopicIds = followedTopicsUserState.topics
                    )
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
                        .map { feed ->
                            ForYouFeedUiState.PopulatedFeed.FeedWithTopicSelection(
                                selectedTopics = availableTopics.map { topic ->
                                    topic to (topic.id in inProgressTopicSelection)
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
        val topics: Set<Int>
    ) : FollowedTopicsState
}

sealed interface ForYouFeedUiState {
    object Loading : ForYouFeedUiState

    sealed interface PopulatedFeed : ForYouFeedUiState {
        val feed: List<NewsResource>

        data class FeedWithTopicSelection(
            val selectedTopics: List<Pair<Topic, Boolean>>,
            override val feed: List<NewsResource>
        ) : PopulatedFeed {
            val canSaveSelectedTopics: Boolean =
                selectedTopics.any { it.second }
        }

        data class FeedWithoutTopicSelection(
            override val feed: List<NewsResource>
        ) : PopulatedFeed
    }
}