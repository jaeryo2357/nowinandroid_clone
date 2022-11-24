package com.example.nowinandroid_clone.core.domain.repository.fake

import com.example.nowinandroid_clone.core.common.network.Dispatcher
import com.example.nowinandroid_clone.core.common.network.NiaDispatchers
import com.example.nowinandroid_clone.core.domain.repository.NewsRepository
import com.example.nowinandroid_clone.core.model.data.NewsResource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.serialization.json.Json
import javax.inject.Inject

class FakeNewsRepository @Inject constructor(
    @Dispatcher(NiaDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val networkJson: Json
) : NewsRepository {
    override fun getNewsResourcesStream(): Flow<List<NewsResource>> = flowOf(emptyList())

    override fun getNewsResourcesStream(filterTopicIds: Set<Int>): Flow<List<NewsResource>> =
        flowOf(emptyList())

    override suspend fun sync(): Boolean = true
}