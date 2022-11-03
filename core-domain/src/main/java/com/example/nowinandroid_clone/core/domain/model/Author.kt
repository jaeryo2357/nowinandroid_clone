package com.example.nowinandroid_clone.core.domain.model

import com.example.nowinandroid_clone.core.database.model.AuthorEntity
import com.example.nowinandroid_clone.core.network.model.NetworkAuthor

fun NetworkAuthor.asEntity() = AuthorEntity(
    id = id,
    name = name,
    imageUrl = imageUrl
)