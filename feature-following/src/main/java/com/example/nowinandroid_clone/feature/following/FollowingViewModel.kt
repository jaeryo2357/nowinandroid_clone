package com.example.nowinandroid_clone.feature.following

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nowinandroid_clone.core.domain.repository.TopicsRepository
import com.example.nowinandroid_clone.core.model.data.FollowableTopic
import com.example.nowinandroid_clone.core.model.data.Topic
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FollowingViewModel @Inject constructor(
    private val topicsRepository: TopicsRepository
) : ViewModel() {

    private val followedTopicIdsStream: Flow<FollowingState> =
        topicsRepository.getFollowedTopicIdsStream()
            .map<Set<Int>, FollowingState> { followTopics ->
                FollowingState.Topics(topics = followTopics)
            }
            .catch { emit(FollowingState.Error) }

    val uiState: StateFlow<FollowingUiState> = combine(
        followedTopicIdsStream,
        topicsRepository.getTopicsStream()
    ) { followedTopicIdsState, topics ->
        if (followedTopicIdsState is FollowingState.Topics) {
            mapFollowedAndUnfollowedTopics(topics)
        } else {
            flowOf(FollowingUiState.Error)
        }
    }.flatMapLatest { it }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = FollowingUiState.Loading
        )

    fun followTopic(followedTopicId: Int, followed: Boolean) {
        viewModelScope.launch {
            topicsRepository.toggleFollowedTopicId(
                followedTopicId, followed
            )
        }
    }

    private fun mapFollowedAndUnfollowedTopics(topics: List<Topic>): Flow<FollowingUiState.Topics> =
        topicsRepository.getFollowedTopicIdsStream().map { followTopicIds ->
            FollowingUiState.Topics(
                topics = topics.map { topic ->
                    FollowableTopic(
                        topic = topic,
                        isFollowed = topic.id in followTopicIds
                    )
                }.sortedBy { it.topic.name }
            )
        }
}

private sealed interface FollowingState {
    data class Topics(val topics: Set<Int>) : FollowingState
    object Error : FollowingState
}

sealed interface FollowingUiState {
    object Loading : FollowingUiState
    data class Topics(val topics: List<FollowableTopic>) :
        FollowingUiState
    object Error : FollowingUiState
}