package com.example.nowinandroid_clone.core.network.retrofit

import com.example.nowinandroid_clone.core.network.model.NetworkNewsResource
import com.example.nowinandroid_clone.core.network.model.NetworkTopic
import retrofit2.http.GET

interface RetrofitNiANetwork {
    @GET(value = "/topics")
    suspend fun getTopics(): List<NetworkTopic>

    @GET(value = "/newsresources")
    suspend fun getNewsResources(): List<NetworkNewsResource>
}