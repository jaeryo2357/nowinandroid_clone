package com.example.nowinandroid_clone.core.domain.model

import com.example.nowinandroid_clone.core.database.model.TopicEntity
import com.example.nowinandroid_clone.core.network.model.NetworkTopic

fun NetworkTopic.asEntity() = TopicEntity(
    id = id,
    name = name,
    description = description,
    followed = followed
)