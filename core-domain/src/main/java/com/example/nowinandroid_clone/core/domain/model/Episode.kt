package com.example.nowinandroid_clone.core.domain.model

import com.example.nowinandroid_clone.core.database.model.EpisodeEntity
import com.example.nowinandroid_clone.core.network.model.NetworkEpisode
import com.example.nowinandroid_clone.core.network.model.NetworkEpisodeExpanded

fun NetworkEpisode.asEntity() = EpisodeEntity(
    id = id,
    name = name,
    publishDate = publishDate,
    alternateVideo = alternateVideo,
    alternateAudio = alternateAudio,
)

fun NetworkEpisodeExpanded.asEntity() = EpisodeEntity(
    id = id,
    name = name,
    publishDate = publishDate,
    alternateVideo = alternateVideo,
    alternateAudio = alternateAudio,
)