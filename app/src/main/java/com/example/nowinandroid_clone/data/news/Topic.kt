package com.example.nowinandroid_clone.data.news

import kotlinx.serialization.Serializable

@Serializable
class Topic(
    val id: Int,
    val name: String,
    val description: String
)