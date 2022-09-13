package com.example.nowinandroid_clone.data.news

import kotlinx.datetime.Instant
import kotlinx.datetime.toInstant
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.util.*

@Serializable
data class NewsResource(
    val id: Int,
    val episodeId: Int,
    val title: String,
    val content: String,
    val url: String,
    val authors: List<Int>,
    @Serializable(InstantSerializer::class)
    val publishDate: Instant,
    val type: String,
    val topics: List<Int>,
    val alternateVideo: VideoInfo
)

@Serializable
data class VideoInfo(
    @SerialName("URL")
    val url: String,
    val startTimestamp: Int,
    val endTimestamp: Int
)

private object InstantSerializer : KSerializer<Instant> {
    override fun deserialize(decoder: Decoder): Instant =
        decoder.decodeString().toInstant()

    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor(
        serialName = "Instant",
        kind = PrimitiveKind.STRING
    )

    override fun serialize(encoder: Encoder, value: Instant) =
        encoder.encodeString(value.toString())
}