package com.example.nowinandroid_clone.core.network.model

import com.example.nowinandroid_clone.core.model.data.NewsResourceType
import com.example.nowinandroid_clone.core.network.util.InstantSerializer
import com.example.nowinandroid_clone.core.network.util.NewsResourceTypeSerializer
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class NetworkNewsResource(
    val id: Int,
    val episodeId: Int,
    val title: String,
    val content: String,
    val url: String,
    @Serializable(InstantSerializer::class)
    val publishDate: Instant,
    @Serializable(NewsResourceTypeSerializer::class)
    val type: NewsResourceType,
    val authors: List<Int> = listOf(),
    val topics: List<Int> = listOf(),
)

@Serializable
data class NetworkNewsResourceExpanded(
    val id: Int,
    val episodeId: Int,
    val title: String,
    val content: String,
    val url: String,
    @Serializable(InstantSerializer::class)
    val publishDate: Instant,
    @Serializable(NewsResourceTypeSerializer::class)
    val type: NewsResourceType,
    val authors: List<NetworkAuthor> = listOf(),
    val topics: List<NetworkTopic> = listOf(),
)