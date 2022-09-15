package com.example.nowinandroid_clone.data.network

import androidx.room.PrimaryKey
import com.example.nowinandroid_clone.data.local.entities.EpisodeEntity
import com.example.nowinandroid_clone.data.network.utilities.InstantSerializer
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class NetworkEpisode(
    @PrimaryKey
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
    @PrimaryKey
    val id: Int,
    val name: String,
    @Serializable(InstantSerializer::class)
    val publishDate: Instant,
    val alternateVideo: String,
    val alternateAudio: String,
    val newsResources: List<NetworkNewsResource> = listOf(),
    val authors: List<NetworkAuthor> = listOf(),
)

fun NetworkEpisode.asEntity() = EpisodeEntity(
    id = id,
    name = name,
    publishDate = publishDate,
    alternateVideo = alternateVideo,
    alternateAudio = alternateAudio,
)

fun NetworkEpisodeExpanded.asEntity() = EpisodeEntity(
    id = id,
    name = name,
    publishDate = publishDate,
    alternateVideo = alternateVideo,
    alternateAudio = alternateAudio,
)