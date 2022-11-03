package com.example.nowinandroid_clone.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class NetworkTopic(
    val id: Int,
    val name: String = "",
    val description: String = "",
    val followed: Boolean = false
)