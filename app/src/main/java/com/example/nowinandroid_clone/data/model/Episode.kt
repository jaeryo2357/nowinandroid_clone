package com.example.nowinandroid_clone.data.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.nowinandroid_clone.data.local.entities.AuthorEntity
import com.example.nowinandroid_clone.data.local.entities.EpisodeAuthorCrossRef
import com.example.nowinandroid_clone.data.local.entities.EpisodeEntity
import com.example.nowinandroid_clone.data.local.entities.NewsResourceEntity

data class Episode(
    @Embedded
    val entity: EpisodeEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "episode_id"
    )
    val newsResources: List<NewsResourceEntity>,
    @Relation(
        parentColumn = "episode_id",
        entityColumn = "author_id",
        associateBy = Junction(EpisodeAuthorCrossRef::class)
    )
    val authors: List<AuthorEntity>
)
