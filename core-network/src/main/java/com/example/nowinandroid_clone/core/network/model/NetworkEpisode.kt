package com.example.nowinandroid_clone.core.network.model

import com.example.nowinandroid_clone.core.network.util.InstantSerializer
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class NetworkEpisode(
    val id: Int,
    val name: String,
    @Serializable(InstantSerializer::class)
    val publishDate: Instant,
    val alternateVideo: String?,
    val alternateAudio: String?,
    val newsResources: List<Int> = listOf(),
    val authors: List<Int> = listOf(),
)

@Serializable
data class NetworkEpisodeExpanded(
    val id: Int,
    val name: String,
    @Serializable(InstantSerializer::class)
    val publishDate: Instant,
    val alternateVideo: String,
    val alternateAudio: String,
    val newsResources: List<NetworkNewsResource> = listOf(),
    val authors: List<NetworkAuthor> = listOf(),
)