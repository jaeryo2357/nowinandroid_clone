package com.example.nowinandroid_clone.core.domain.repository.fake

import com.example.nowinandroid_clone.core.common.network.Dispatcher
import com.example.nowinandroid_clone.core.common.network.NiaDispatchers
import com.example.nowinandroid_clone.core.database.model.NewsResourceEntity
import com.example.nowinandroid_clone.core.database.model.asExternalModel
import com.example.nowinandroid_clone.core.domain.model.asEntity
import com.example.nowinandroid_clone.core.domain.repository.NewsRepository
import com.example.nowinandroid_clone.core.model.data.NewsResource
import com.example.nowinandroid_clone.core.network.fake.FakeDataSource
import com.example.nowinandroid_clone.core.network.model.NetworkNewsResource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.serialization.json.Json
import kotlinx.serialization.decodeFromString
import javax.inject.Inject

class FakeNewsRepository @Inject constructor(
    @Dispatcher(NiaDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val networkJson: Json
) : NewsRepository {
    override fun getNewsResourcesStream(): Flow<List<NewsResource>> = flow {
        emit(
            networkJson.decodeFromString<List<NetworkNewsResource>>(FakeDataSource.data)
                .map(NetworkNewsResource::asEntity)
                .map(NewsResourceEntity::asExternalModel)
        )
    }.flowOn(ioDispatcher)

    override fun getNewsResourcesStream(filterTopicIds: Set<Int>): Flow<List<NewsResource>> =
        flow {
            emit(
                networkJson.decodeFromString<List<NetworkNewsResource>>(FakeDataSource.data)
                    .filter { it.topics.intersect(filterTopicIds).isNotEmpty() }
                    .map(NetworkNewsResource::asEntity)
                    .map(NewsResourceEntity::asExternalModel)
            )
        }.flowOn(ioDispatcher)

    override suspend fun sync(): Boolean = true
}