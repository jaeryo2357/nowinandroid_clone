package com.example.nowinandroid_clone.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.nowinandroid_clone.core.model.data.Author

@Entity(
    tableName = "authors",
    indices = [
        Index(value = ["name"], unique = true)
    ],
)
data class AuthorEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    @ColumnInfo(name = "image_url")
    val imageUrl: String
)

fun AuthorEntity.asExternalModel() = Author(
    id = id,
    name = name,
    imageUrl = imageUrl
)