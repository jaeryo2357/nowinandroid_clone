package com.example.nowinandroid_clone.core.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

/**
 * many to many
 */
@Entity(
    tableName = "episodes_authors",
    primaryKeys = ["episode_id", "author_id"],
    foreignKeys = [
        ForeignKey(
            entity = EpisodeEntity::class,
            parentColumns = ["id"],
            childColumns = ["episode_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = AuthorEntity::class,
            parentColumns = ["id"],
            childColumns = ["author_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class EpisodeAuthorCrossRef(
    @ColumnInfo(name = "episode_id")
    val episode: Int,
    @ColumnInfo(name = "author_id")
    val authorId: Long
)