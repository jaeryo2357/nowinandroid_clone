package com.example.nowinandroid_clone.core.model.data

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.nowinandroid_clone.core.model.entities.AuthorEntity
import com.example.nowinandroid_clone.core.model.entities.EpisodeAuthorCrossRef
import com.example.nowinandroid_clone.core.model.entities.EpisodeEntity
import com.example.nowinandroid_clone.core.model.entities.NewsResourceEntity

data class Episode(
    @Embedded
    val entity: EpisodeEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "episode_id"
    )
    val newsResources: List<NewsResourceEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = EpisodeAuthorCrossRef::class,
            parentColumn = "episode_id",
            entityColumn = "author_id"
        )
    )
    val authors: List<AuthorEntity>
)
