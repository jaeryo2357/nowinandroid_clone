package com.example.nowinandroid_clone.core.network.fake

import com.example.nowinandroid_clone.core.common.network.Dispatcher
import com.example.nowinandroid_clone.core.common.network.NiaDispatchers
import com.example.nowinandroid_clone.core.network.model.NetworkNewsResource
import com.example.nowinandroid_clone.core.network.NiANetwork
import com.example.nowinandroid_clone.core.network.model.NetworkTopic
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class FakeNiANetwork @Inject constructor(
    @Dispatcher(NiaDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val networkJson: Json
) : NiANetwork {
    override suspend fun getTopics(): List<NetworkTopic> =
        withContext(ioDispatcher) {
            networkJson.decodeFromString(FakeDataSource.topicsData)
        }

    override suspend fun getNewsResources(): List<NetworkNewsResource> =
        withContext(ioDispatcher) {
            networkJson.decodeFromString<ResourceData>(
                FakeDataSource.data
            ).resources
        }
}

@Serializable
private data class ResourceData(
    val resources: List<NetworkNewsResource>
)