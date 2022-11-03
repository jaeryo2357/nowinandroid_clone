package com.example.nowinandroid_clone.core.database.model

import com.example.nowinandroid_clone.core.model.data.Author
import com.example.nowinandroid_clone.core.model.data.Episode
import com.example.nowinandroid_clone.core.model.data.NewsResource
import com.example.nowinandroid_clone.core.model.data.NewsResourceType
import kotlinx.datetime.Instant
import org.junit.Assert.assertEquals
import org.junit.Test

class PopulatedEpisodeKtTest {
    @Test
    fun populated_episode_can_be_mapped_to_episode() {
        val populatedEpisode = PopulatedEpisode(
            entity = EpisodeEntity(
                id = 0,
                name = "Test",
                publishDate = Instant.fromEpochMilliseconds(1),
                alternateAudio = "audio",
                alternateVideo = "video"
            ),
            newsResources = listOf(
                NewsResourceEntity(
                    id = 1,
                    episodeId = 0,
                    title = "news",
                    content = "Hilt",
                    url = "url",
                    type = NewsResourceType.Video,
                    publishDate = Instant.fromEpochMilliseconds(1),
                )
            ),
            authors = listOf(
                AuthorEntity(
                    id = 2,
                    name = "name",
                    imageUrl = "imageUrl"
                )
            ),
        )
        val episode = populatedEpisode.asExternalModel()

        assertEquals(
            Episode(
                id = 0,
                name = "Test",
                publishDate = Instant.fromEpochMilliseconds(1),
                alternateAudio = "audio",
                alternateVideo = "video",
                newsResources = listOf(
                    NewsResource(
                        id = 1,
                        episodeId = 0,
                        title = "news",
                        content = "Hilt",
                        url = "url",
                        type = NewsResourceType.Video,
                        publishDate = Instant.fromEpochMilliseconds(1),
                        authors = listOf(),
                        topics = listOf()
                    )
                ),
                authors = listOf(
                    Author(
                        id = 2,
                        name = "name",
                        imageUrl = "imageUrl"
                    )
                ),
            ),
            episode
        )
    }
}