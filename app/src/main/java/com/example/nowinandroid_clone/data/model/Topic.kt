package com.example.nowinandroid_clone.data.model

import kotlinx.serialization.Serializable

@Serializable
class Topic(
    val id: Int,
    val name: String,
    val description: String,
    val followed: Boolean = false
)