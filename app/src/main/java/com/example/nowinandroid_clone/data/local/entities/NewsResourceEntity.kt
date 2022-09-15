package com.example.nowinandroid_clone.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.datetime.Instant

@Entity(
    tableName = "news_resources",
    foreignKeys = [
        ForeignKey(
            entity = EpisodeEntity::class,
            parentColumns = ["id"],
            childColumns = ["episode_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class NewsResourceEntity(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "episode_id")
    val episodeId: Int,
    val title: String,
    val content: String,
    val url: String,
    @ColumnInfo(name = "publish_date")
    val publishDate: Instant,
    val type: String
)
