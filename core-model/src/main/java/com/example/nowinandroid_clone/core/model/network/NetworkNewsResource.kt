package com.example.nowinandroid_clone.core.model.network

import com.example.nowinandroid_clone.core.model.entities.NewsResourceEntity
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
    val type: String,
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
    val type: String,
    val authors: List<NetworkAuthor> = listOf(),
    val topics: List<NetworkTopic> = listOf(),
)

fun NetworkNewsResource.asEntity() =
    NewsResourceEntity(
        id = id,
        episodeId = episodeId,
        title = title,
        content = content,
        url = url,
        publishDate = publishDate,
        type = type,
    )

fun NetworkNewsResourceExpanded.asEntity() =
    NewsResourceEntity(
        id = id,
        episodeId = episodeId,
        title = title,
        content = content,
        url = url,
        publishDate = publishDate,
        type = type,
    )