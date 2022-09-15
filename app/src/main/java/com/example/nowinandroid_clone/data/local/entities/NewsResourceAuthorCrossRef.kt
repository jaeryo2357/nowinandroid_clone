package com.example.nowinandroid_clone.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "news_resources_authors",
    primaryKeys = ["news_resource_id", "author_id"],
    foreignKeys = [
        ForeignKey(
            entity = NewsResourceEntity::class,
            parentColumns = ["id"],
            childColumns = ["news_resource_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = AuthorEntity::class,
            parentColumns = ["id"],
            childColumns = ["author_id"],
            onDelete = ForeignKey.CASCADE
        ),
    ]
)
data class NewsResourceAuthorCrossRef(
    @ColumnInfo(name = "news_resource_id")
    val newsResourceId: Int,
    @ColumnInfo(name = "author_id")
    val authorId: Long
)
