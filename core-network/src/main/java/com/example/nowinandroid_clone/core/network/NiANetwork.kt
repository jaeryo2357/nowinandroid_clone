package com.example.nowinandroid_clone.core.network

import com.example.nowinandroid_clone.core.network.model.NetworkNewsResource
import com.example.nowinandroid_clone.core.network.model.NetworkTopic

interface NiANetwork {
    suspend fun getTopics(): List<NetworkTopic>

    suspend fun getNewsResources(): List<NetworkNewsResource>
}