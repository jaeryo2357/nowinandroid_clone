package com.example.nowinandroid_clone.core.domain.repository

import com.example.nowinandroid_clone.core.model.data.Topic
import kotlinx.coroutines.flow.Flow

interface TopicsRepository {
    fun getTopicsStream(): Flow<List<Topic>>

    suspend fun setFollowedTopicIds(followedTopicIds: Set<Int>)

    suspend fun toggleFollowedTopicId(followedTopicId: Int, followed: Boolean)

    fun getFollowedTopicIdsStream(): Flow<Set<Int>>


    /**
     * Synchronizes the local database in backing the repository with the network.
     * Returns if the sync was successful or not.
     */
    suspend fun sync(): Boolean
}