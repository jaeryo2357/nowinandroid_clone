package com.example.nowinandroid_clone.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class NetworkAuthor(
    val id: Int,
    val name: String,
    val imageUrl: String
)
