package com.example.nowinandroid_clone.data.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.nowinandroid_clone.data.local.entities.*

data class NewsResource(
    @Embedded
    val entity: NewsResourceEntity,
    @Relation(
        parentColumn = "episode_id",
        entityColumn = "id"
    )
    val episode: EpisodeEntity,
    @Relation(
        parentColumn = "news_resource_id",
        entityColumn = "author_id",
        associateBy =
            Junction(NewsResourceAuthorCrossRef::class)
    )
    val authors: List<AuthorEntity>,
    @Relation(
        parentColumn = "news_resource_id",
        entityColumn = "topic_id",
        associateBy = Junction(NewsResourceAuthorCrossRef::class)
    )
    val topics: List<TopicEntity>
)