package com.example.nowinandroid_clone.core.network.model.util

import com.example.nowinandroid_clone.core.model.data.NewsResourceType
import com.example.nowinandroid_clone.core.network.util.NewsResourceTypeSerializer
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Test

class NewsResourceTypeSerializerTest {

    @Test
    fun test_news_resource_serializer_video() {
        assertEquals(
            NewsResourceType.Video,
            Json.decodeFromString(NewsResourceTypeSerializer, """"Video 📺"""")
        )
        """"
        """".trimMargin()
    }

    @Test
    fun test_news_resource_serializer_article() {
        assertEquals(
            NewsResourceType.Article,
            Json.decodeFromString(NewsResourceTypeSerializer, """"Article 📚"""")
        )
    }

    @Test
    fun test_news_resource_serializer_api_change() {
        assertEquals(
            NewsResourceType.APIChange,
            Json.decodeFromString(NewsResourceTypeSerializer, """"API change"""")
        )
    }

    @Test
    fun test_news_resource_serializer_codelab() {
        assertEquals(
            NewsResourceType.Codelab,
            Json.decodeFromString(NewsResourceTypeSerializer, """"Codelab"""")
        )
    }

    @Test
    fun test_news_resource_serializer_podcast() {
        assertEquals(
            NewsResourceType.Podcast,
            Json.decodeFromString(NewsResourceTypeSerializer, """"Podcast 🎙"""")
        )
    }

    @Test
    fun test_news_resource_serializer_docs() {
        assertEquals(
            NewsResourceType.Docs,
            Json.decodeFromString(NewsResourceTypeSerializer, """"Docs 📑"""")
        )
    }

    @Test
    fun test_news_resource_serializer_event() {
        assertEquals(
            NewsResourceType.Event,
            Json.decodeFromString(NewsResourceTypeSerializer, """"Event 📆"""")
        )
    }

    @Test
    fun test_news_resource_serializer_dac() {
        assertEquals(
            NewsResourceType.DAC,
            Json.decodeFromString(NewsResourceTypeSerializer, """"DAC"""")
        )
    }

    @Test
    fun test_news_resource_serializer_unknown() {
        assertEquals(
            NewsResourceType.Unknown,
            Json.decodeFromString(NewsResourceTypeSerializer, """"umm"""")
        )
    }
}