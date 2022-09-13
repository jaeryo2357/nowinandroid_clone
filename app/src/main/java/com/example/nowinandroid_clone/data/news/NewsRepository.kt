package com.example.nowinandroid_clone.data.news

import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getNewsResourcesStream(): Flow<List<NewsResource>>

    fun getNewsResourcesStream(filterTopicIds: Set<Int>): Flow<List<NewsResource>>
}