package com.example.nowinandroid_clone.data.network

import com.example.nowinandroid_clone.data.local.entities.TopicEntity
import kotlinx.serialization.Serializable

@Serializable
data class NetworkTopic(
    val id: Int,
    val name: String = "",
    val description: String = ""
)

fun NetworkTopic.asEntity() = TopicEntity(
    id = id,
    name = name,
    description = description
)
