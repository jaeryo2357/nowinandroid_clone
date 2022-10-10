package com.example.nowinandroid_clone.core.model

import com.example.nowinandroid_clone.core.model.network.*
import kotlinx.datetime.Instant
import org.junit.Assert.assertEquals
import org.junit.Test

class NetworkEntityKtTest {

    @Test
    fun network_author_can_be_mapped_to_author_entity() {
        val networkModel = NetworkAuthor(
            id = 0,
            name = "Test",
            imageUrl = "something"
        )
        val entity = networkModel.asEntity()

        assertEquals(0, entity.id)
        assertEquals("Test", entity.name)
        assertEquals("something", entity.imageUrl)
    }

    @Test
    fun network_topic_can_be_mapped_to_topic_entity() {
        val networkModel = NetworkTopic(
            id = 0,
            name = "Test",
            description = "something"
        )
        val entity = networkModel.asEntity()

        assertEquals(0, entity.id)
        assertEquals("Test", entity.name)
        assertEquals("something", entity.description)
    }

    @Test
    fun network_news_resource_can_be_mapped_to_news_resource_entity() {
        val networkModel = NetworkNewsResource(
            id = 0,
            episodeId = 2,
            title = "title",
            content = "content",
            url = "url",
            publishDate = Instant.fromEpochMilliseconds(1),
            type = com.example.nowinandroid_clone.core.model.data.NewsResourceType.Article.displayText,
        )
        val entity = networkModel.asEntity()

        assertEquals(0, entity.id)
        assertEquals(2, entity.episodeId)
        assertEquals("title", entity.title)
        assertEquals("content", entity.content)
        assertEquals("url", entity.url)
        assertEquals(Instant.fromEpochMilliseconds(1), entity.publishDate)
        assertEquals(com.example.nowinandroid_clone.core.model.data.NewsResourceType.Article.displayText, entity.type)

        val expandedNetworkModel = NetworkNewsResourceExpanded(
            id = 0,
            episodeId = 2,
            title = "title",
            content = "content",
            url = "url",
            publishDate = Instant.fromEpochMilliseconds(1),
            type = com.example.nowinandroid_clone.core.model.data.NewsResourceType.Article.displayText,
        )

        val entityFromExpanded = expandedNetworkModel.asEntity()

        assertEquals(0, entityFromExpanded.id)
        assertEquals(2, entityFromExpanded.episodeId)
        assertEquals("title", entityFromExpanded.title)
        assertEquals("content", entityFromExpanded.content)
        assertEquals("url", entityFromExpanded.url)
        assertEquals(Instant.fromEpochMilliseconds(1), entityFromExpanded.publishDate)
        assertEquals(com.example.nowinandroid_clone.core.model.data.NewsResourceType.Article.displayText, entityFromExpanded.type)
    }

    @Test
    fun network_episode_can_be_mapped_to_episode_entity() {
        val networkModel = NetworkEpisode(
            id = 0,
            name = "name",
            publishDate = Instant.fromEpochMilliseconds(1),
            alternateVideo = "alternateVideo",
            alternateAudio = "alternateAudio",
        )
        val entity = networkModel.asEntity()

        assertEquals(0, entity.id)
        assertEquals("name", entity.name)
        assertEquals("alternateVideo", entity.alternateVideo)
        assertEquals("alternateAudio", entity.alternateAudio)
        assertEquals(Instant.fromEpochMilliseconds(1), entity.publishDate)

        val expandedNetworkModel = NetworkEpisodeExpanded(
            id = 0,
            name = "name",
            publishDate = Instant.fromEpochMilliseconds(1),
            alternateVideo = "alternateVideo",
            alternateAudio = "alternateAudio",
        )
    }
}