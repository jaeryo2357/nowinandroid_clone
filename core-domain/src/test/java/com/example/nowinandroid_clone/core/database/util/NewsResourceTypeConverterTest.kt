package com.example.nowinandroid_clone.core.database.util

import com.example.nowinandroid_clone.core.database.utilities.NewsResourceTypeConverter
import com.example.nowinandroid_clone.core.model.data.NewsResourceType
import org.junit.Assert.assertEquals
import org.junit.Test

class NewsResourceTypeConverterTest {

    @Test
    fun test_room_news_resource_type_converter_for_video() {
        assertEquals(
            NewsResourceType.Video,
            NewsResourceTypeConverter().stringToNewsResourceType("Video 📺")
        )
    }

    @Test
    fun test_room_news_resource_type_converter_for_article() {
        assertEquals(
            NewsResourceType.Article,
            NewsResourceTypeConverter().stringToNewsResourceType("Article 📚")
        )
    }

    @Test
    fun test_room_news_resource_type_converter_for_api_change() {
        assertEquals(
            NewsResourceType.APIChange,
            NewsResourceTypeConverter().stringToNewsResourceType("API change")
        )
    }

    @Test
    fun test_room_news_resource_type_converter_for_codelab() {
        assertEquals(
            NewsResourceType.Codelab,
            NewsResourceTypeConverter().stringToNewsResourceType("Codelab")
        )
    }

    @Test
    fun test_room_news_resource_type_converter_for_podcast() {
        assertEquals(
            NewsResourceType.Podcast,
            NewsResourceTypeConverter().stringToNewsResourceType("Podcast 🎙")
        )
    }

    @Test
    fun test_room_news_resource_type_converter_for_docs() {
        assertEquals(
            NewsResourceType.Docs,
            NewsResourceTypeConverter().stringToNewsResourceType("Docs 📑")
        )
    }

    @Test
    fun test_room_news_resource_type_converter_for_event() {
        assertEquals(
            NewsResourceType.Event,
            NewsResourceTypeConverter().stringToNewsResourceType("Event 📆")
        )
    }

    @Test
    fun test_room_news_resource_type_converter_for_dac() {
        assertEquals(
            NewsResourceType.DAC,
            NewsResourceTypeConverter().stringToNewsResourceType("DAC")
        )
    }

    @Test
    fun test_room_news_resource_type_converter_for_umm() {
        assertEquals(
            NewsResourceType.Unknown,
            NewsResourceTypeConverter().stringToNewsResourceType("umm")
        )
    }
}