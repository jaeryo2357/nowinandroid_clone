package com.example.nowinandroid_clone.data.repository

import com.example.nowinandroid_clone.data.model.NewsResource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getNewsResourcesStream(): Flow<List<NewsResource>>

    fun getNewsResourcesStream(filterTopicIds: Set<Int>): Flow<List<NewsResource>>
}