package com.example.nowinandroid_clone.core.database.model

import com.example.nowinandroid_clone.core.model.data.Author
import com.example.nowinandroid_clone.core.model.data.NewsResource
import com.example.nowinandroid_clone.core.model.data.NewsResourceType
import com.example.nowinandroid_clone.core.model.data.Topic
import kotlinx.datetime.Instant
import org.junit.Assert.assertEquals
import org.junit.Test

class PopulatedNewsResourceKtTest {
    @Test
    fun populated_news_resource_can_be_mapped_to_news_resource() {
        val populatedNewsResource = PopulatedNewsResource(
            entity = NewsResourceEntity(
                id = 1,
                episodeId = 0,
                title = "news",
                content = "Hilt",
                url = "url",
                type = NewsResourceType.Video,
                publishDate = Instant.fromEpochMilliseconds(1),
            ),
            episode = EpisodeEntity(
                id = 4,
                name = "episode 4",
                publishDate = Instant.fromEpochMilliseconds(2),
                alternateAudio = "audio",
                alternateVideo = "video",
            ),
            authors = listOf(
                AuthorEntity(
                    id = 2,
                    name = "name",
                    imageUrl = "imageUrl"
                )
            ),
            topics = listOf(
                TopicEntity(
                    id = 3,
                    name = "name",
                    description = "description",
                    followed = true,
                )
            ),
        )
        val newsResource = populatedNewsResource.asExternalModel()

        assertEquals(
            NewsResource(
                id = 1,
                episodeId = 0,
                title = "news",
                content = "Hilt",
                url = "url",
                type = NewsResourceType.Video,
                publishDate = Instant.fromEpochMilliseconds(1),
                authors = listOf(
                    Author(
                        id = 2,
                        name = "name",
                        imageUrl = "imageUrl"
                    )
                ),
                topics = listOf(
                    Topic(
                        id = 3,
                        name = "name",
                        description = "description",
                        followed = true,
                    )
                )
            ),
            newsResource
        )
    }
}