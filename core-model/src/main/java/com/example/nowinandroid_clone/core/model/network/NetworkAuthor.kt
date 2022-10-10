package com.example.nowinandroid_clone.core.model.network

import com.example.nowinandroid_clone.core.model.entities.AuthorEntity
import kotlinx.serialization.Serializable

@Serializable
data class NetworkAuthor(
    val id: Int,
    val name: String,
    val imageUrl: String
)

fun NetworkAuthor.asEntity() = AuthorEntity(
    id = id,
    name = name,
    imageUrl = imageUrl
)
