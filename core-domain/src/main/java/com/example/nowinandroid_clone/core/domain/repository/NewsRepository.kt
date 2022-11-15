package com.example.nowinandroid_clone.core.domain.repository

import com.example.nowinandroid_clone.core.model.data.NewsResource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getNewsResourcesStream(): Flow<List<NewsResource>>

    fun getNewsResourcesStream(filterTopicIds: Set<Int>): Flow<List<NewsResource>>


    /**
     * Synchronizes the local database in backing the repository with the network.
     * Returns if the sync was successful or not.
     */
    suspend fun sync(): Boolean
}