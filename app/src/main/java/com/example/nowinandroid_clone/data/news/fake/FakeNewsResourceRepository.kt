package com.example.nowinandroid_clone.data.news.fake

import com.example.nowinandroid_clone.data.news.NewsResource
import com.example.nowinandroid_clone.data.news.NewsResourceRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class FakeNewsResourceRepository(
    private val ioDispatcher: CoroutineDispatcher
) : NewsResourceRepository {
    private val deserializer = Json { ignoreUnknownKeys = true }

    override fun monitor(): Flow<List<NewsResource>> =
        flow {
            emit(deserializer.decodeFromString<ResourceData>(FakeDataSource.data).resources)
        }.flowOn(ioDispatcher)
}

@Serializable
private data class ResourceData(
    val resources: List<NewsResource>
)