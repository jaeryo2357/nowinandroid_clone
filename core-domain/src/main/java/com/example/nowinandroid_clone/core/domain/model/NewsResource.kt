package com.example.nowinandroid_clone.core.domain.model

import com.example.nowinandroid_clone.core.database.model.NewsResourceEntity
import com.example.nowinandroid_clone.core.network.model.NetworkNewsResource
import com.example.nowinandroid_clone.core.network.model.NetworkNewsResourceExpanded

fun NetworkNewsResource.asEntity() = NewsResourceEntity(
    id = id,
    episodeId = episodeId,
    title = title,
    content = content,
    url = url,
    publishDate = publishDate,
    type = type,
)

fun NetworkNewsResourceExpanded.asEntity() = NewsResourceEntity(
    id = id,
    episodeId = episodeId,
    title = title,
    content = content,
    url = url,
    publishDate = publishDate,
    type = type,
)