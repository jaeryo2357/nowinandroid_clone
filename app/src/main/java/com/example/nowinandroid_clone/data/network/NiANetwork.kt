package com.example.nowinandroid_clone.data.network

interface NiANetwork {
    suspend fun getNewsResources(): List<NetworkNewsResource>
}