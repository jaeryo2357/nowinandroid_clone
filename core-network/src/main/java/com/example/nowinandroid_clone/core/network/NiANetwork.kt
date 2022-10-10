package com.example.nowinandroid_clone.core.network

import com.example.nowinandroid_clone.core.model.network.NetworkNewsResource
import com.example.nowinandroid_clone.core.model.network.NetworkTopic

interface NiANetwork {
    suspend fun getTopics(): List<NetworkTopic>

    suspend fun getNewsResources(): List<NetworkNewsResource>
}