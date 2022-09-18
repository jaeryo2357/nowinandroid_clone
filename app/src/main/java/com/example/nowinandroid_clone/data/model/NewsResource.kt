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
        parentColumn = "id",
        entityColumn = "id",
        associateBy =
            Junction(
                value = NewsResourceAuthorCrossRef::class,
                parentColumn = "news_resource_id",
                entityColumn = "author_id"
            )
    )
    val authors: List<AuthorEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = NewsResourceTopicCrossRef::class,
            parentColumn = "news_resource_id",
            entityColumn = "topic_id"
        )
    )
    val topics: List<TopicEntity>
)