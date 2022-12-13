package com.example.nowinandroid_clone.core.testing.repository

import com.example.nowinandroid_clone.core.domain.repository.NewsRepository
import com.example.nowinandroid_clone.core.model.data.NewsResource
import com.example.nowinandroid_clone.core.model.data.Topic
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.map

class TestNewsRepository : NewsRepository {
    private val newsResourceFlow: MutableSharedFlow<List<NewsResource>> = MutableSharedFlow(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    override fun getNewsResourcesStream(): Flow<List<NewsResource>> = newsResourceFlow

    override fun getNewsResourcesStream(filterTopicIds: Set<Int>): Flow<List<NewsResource>> =
        getNewsResourcesStream().map { newsResources ->
            newsResources.filter {
                it.topics.map(Topic::id).intersect(filterTopicIds).isNotEmpty()
            }
        }

    override suspend fun sync(): Boolean = true

    fun sendNewsResources(newsResources: List<NewsResource>) {
        newsResourceFlow.tryEmit(newsResources)
    }
}