package com.example.nowinandroid_clone.core.model.data

import kotlinx.datetime.Instant

data class NewsResource(
    val id: Int,
    val episodeId: Int,
    val title: String,
    val content: String,
    val url: String,
    val publishDate: Instant,
    val type: NewsResourceType,
    val authors: List<Author>,
    val topics: List<Topic>
)