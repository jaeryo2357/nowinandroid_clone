package com.example.nowinandroid_clone

import com.example.nowinandroid_clone.data.repository.NewsRepository
import com.example.nowinandroid_clone.data.model.NewsResource
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
                it.topics.intersect(filterTopicIds).isNotEmpty()
            }
        }

    fun sendNewsResources(newsResources: List<NewsResource>) {
        newsResourceFlow.tryEmit(newsResources)
    }
}