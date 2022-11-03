package com.example.nowinandroid_clone.core.model.data

import kotlinx.datetime.Instant

data class Episode(
    val id: Int,
    val name: String,
    val publishDate: Instant,
    val alternateVideo: String?,
    val alternateAudio: String?,
    val newsResources: List<NewsResource>,
    val authors: List<Author>
)
