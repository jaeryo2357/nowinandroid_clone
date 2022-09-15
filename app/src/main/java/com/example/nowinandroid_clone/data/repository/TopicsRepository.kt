package com.example.nowinandroid_clone.data.repository

import com.example.nowinandroid_clone.data.model.Topic
import kotlinx.coroutines.flow.Flow

interface TopicsRepository {
    fun getTopicsStream(): Flow<List<Topic>>

    suspend fun setFollowedTopicIds(followedTopicIds: Set<Int>)

    fun getFollowedTopicIdsStream(): Flow<Set<Int>>
}