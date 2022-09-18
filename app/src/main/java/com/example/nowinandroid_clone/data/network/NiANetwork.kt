package com.example.nowinandroid_clone.data.network

interface NiANetwork {
    suspend fun getTopics(): List<NetworkTopic>

    suspend fun getNewsResources(): List<NetworkNewsResource>
}