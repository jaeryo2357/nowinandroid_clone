package com.example.nowinandroid_clone.core.testing.repository

import com.example.nowinandroid_clone.core.model.data.Topic
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class TestTopicsRepository :
    com.example.nowinandroid_clone.core.domain.repository.TopicsRepository {
    /**
     * The backing hot flow for the list of followed topic ids for testing.
     */
    private val _followedTopicIds: MutableSharedFlow<Set<Int>> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    /**
     * The backing hot flow for the list of topics ids for testing.
     */
    private val topicsFlow: MutableSharedFlow<List<Topic>> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    override fun getTopicsStream(): Flow<List<Topic>> = topicsFlow

    override suspend fun setFollowedTopicIds(followedTopicIds: Set<Int>) {
        _followedTopicIds.tryEmit(followedTopicIds)
    }

    override suspend fun toggleFollowedTopicId(followedTopicId: Int, followed: Boolean) {
        getCurrentFollowedTopics()?.let { current ->
            _followedTopicIds.tryEmit(
                if (followed) current.plus(followedTopicId)
                else current.minus(followedTopicId)
            )
        }
    }

    override fun getFollowedTopicIdsStream(): Flow<Set<Int>> = _followedTopicIds

    override suspend fun sync(): Boolean = true

    /**
     * A test-only API to allow controlling the list of topics from tests.
     */
    fun sendTopics(topics: List<Topic>) {
        topicsFlow.tryEmit(topics)
    }

    /**
     * A test-only API to allow querying the current followed topics.
     */
    fun getCurrentFollowedTopics(): Set<Int>? = _followedTopicIds.replayCache.firstOrNull()
}