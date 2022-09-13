package com.example.nowinandroid_clone.data.news.fake

import com.example.nowinandroid_clone.data.news.NewsResource
import com.example.nowinandroid_clone.data.news.NewsRepository
import com.example.nowinandroid_clone.di.NiaDispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class FakeNewsRepository @Inject constructor(
    private val dispatcher: NiaDispatchers,
    private val networkJson: Json
) : NewsRepository {
    override fun getNewsResourcesStream(): Flow<List<NewsResource>> =
        flow {
            emit(networkJson.decodeFromString<ResourceData>(FakeDataSource.data).resources)
        }.flowOn(dispatcher.IO)

    override fun getNewsResourcesStream(filterTopicIds: Set<Int>): Flow<List<NewsResource>> =
        getNewsResourcesStream().map { newsResource ->
            newsResource.filter {
                it.topics.intersect(filterTopicIds.toSet()).isNotEmpty()
            }
        }
}

@Serializable
private data class ResourceData(
    val resources: List<NewsResource>
)