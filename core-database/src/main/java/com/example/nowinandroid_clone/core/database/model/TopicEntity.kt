package com.example.nowinandroid_clone.core.database.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.nowinandroid_clone.core.model.data.Topic

@Entity(
    tableName = "topics",
    indices = [
        Index(value = ["name"], unique = true)
    ]
)
data class TopicEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String
)

fun TopicEntity.asExternalModel() = Topic(
    id = id,
    name = name,
    description = description
)