package com.example.nowinandroid_clone.data.news

import java.util.*

data class NewsResource(
    val episode: Int,
    val title: String,
    val content: String,
    val url: String,
    val authorName: String,
    val publicData: Date,
    val type: String,
    val topics: List<String>,
    val alternateVideo: VideoInfo
)

data class VideoInfo(
    val url: String,
    val startTimestamp: String,
    val endTimestamp: String
)