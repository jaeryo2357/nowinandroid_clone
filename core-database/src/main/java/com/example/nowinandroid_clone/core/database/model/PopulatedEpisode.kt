package com.example.nowinandroid_clone.core.database.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.nowinandroid_clone.core.model.data.Episode

data class PopulatedEpisode(
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
            entityColumn = "author_id",
        )
    )
    val authors: List<AuthorEntity>
)

fun PopulatedEpisode.asExternalModel() = Episode(
    id = entity.id,
    name = entity.name,
    publishDate = entity.publishDate,
    alternateVideo = entity.alternateVideo,
    alternateAudio = entity.alternateAudio,
    newsResources = newsResources.map(NewsResourceEntity::asExternalModel),
    authors = authors.map(AuthorEntity::asExternalModel)
)
