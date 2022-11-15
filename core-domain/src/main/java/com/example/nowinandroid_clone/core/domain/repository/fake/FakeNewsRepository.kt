package com.example.nowinandroid_clone.core.domain.repository.fake

import com.example.nowinandroid_clone.core.domain.repository.NewsRepository
import com.example.nowinandroid_clone.core.network.NiaDispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.serialization.json.Json
import javax.inject.Inject

class FakeNewsRepository @Inject constructor(
    private val dispatcher: NiaDispatchers,
    private val networkJson: Json
) : NewsRepository {
    override fun getNewsResourcesStream(): Flow<List<com.example.nowinandroid_clone.core.model.data.NewsResource>> = flowOf(emptyList())

    override fun getNewsResourcesStream(filterTopicIds: Set<Int>): Flow<List<com.example.nowinandroid_clone.core.model.data.NewsResource>> =
        flowOf(emptyList())

    override suspend fun sync(): Boolean = true
}