package com.example.nowinandroid_clone.core.model.network

import com.example.nowinandroid_clone.core.model.entities.TopicEntity
import kotlinx.serialization.Serializable

@Serializable
data class NetworkTopic(
    val id: Int,
    val name: String = "",
    val description: String = "",
    val followed: Boolean = false
)

fun NetworkTopic.asEntity() = TopicEntity(
    id = id,
    name = name,
    description = description,
    followed = followed
)
