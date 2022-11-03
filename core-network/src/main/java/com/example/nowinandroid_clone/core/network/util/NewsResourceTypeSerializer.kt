package com.example.nowinandroid_clone.core.network.util

import com.example.nowinandroid_clone.core.model.data.NewsResourceType
import com.example.nowinandroid_clone.core.model.data.asNewsResourceType
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object NewsResourceTypeSerializer:
KSerializer<NewsResourceType> {
    override fun deserialize(decoder: Decoder): NewsResourceType =
        decoder.decodeString().asNewsResourceType()

    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor(
            serialName = "type",
            kind = PrimitiveKind.STRING
        )

    override fun serialize(encoder: Encoder, value: NewsResourceType) =
        encoder.encodeString(value.name)
}