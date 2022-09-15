package com.example.nowinandroid_clone.data.fake

import com.example.nowinandroid_clone.data.network.NetworkNewsResource
import com.example.nowinandroid_clone.data.network.NiANetwork
import com.example.nowinandroid_clone.di.NiaDispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class FakeNiaNetwork(
    private val dispatchers: NiaDispatchers,
    private val networkJson: Json
) : NiANetwork {
    override suspend fun getNewsResources(): List<NetworkNewsResource> =
        withContext(dispatchers.IO) {
            networkJson.decodeFromString<ResourceData>(
                FakeDataSource.data
            ).resources
        }
}

@Serializable
private data class ResourceData(
    val resources: List<NetworkNewsResource>
)