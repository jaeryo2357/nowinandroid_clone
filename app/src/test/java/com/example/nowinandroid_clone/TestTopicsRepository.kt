package com.example.nowinandroid_clone

import com.example.nowinandroid_clone.data.model.Topic
import com.example.nowinandroid_clone.data.repository.TopicsRepository
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class TestTopicsRepository : TopicsRepository {
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

    override fun getFollowedTopicIdsStream(): Flow<Set<Int>> = _followedTopicIds

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