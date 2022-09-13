package com.example.nowinandroid_clone.data.news

import kotlinx.coroutines.flow.Flow

interface TopicsRepository {
    fun getTopicsStream(): Flow<List<Topic>>

    suspend fun setFollowedTopicIds(followedTopicIds: Set<Int>)

    fun getFollowedTopicIdsStream(): Flow<Set<Int>>
}