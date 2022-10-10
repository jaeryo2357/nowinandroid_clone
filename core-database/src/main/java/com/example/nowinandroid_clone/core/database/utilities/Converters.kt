package com.example.nowinandroid_clone.core.database.utilities

import androidx.room.TypeConverter
import kotlinx.datetime.Instant

class InstantConverter {
    @TypeConverter
    fun longToInstant(value: Long?): Instant? =
        value?.let(Instant::fromEpochMilliseconds)

    @TypeConverter
    fun instantToLong(instant: Instant?): Long? =
        instant?.toEpochMilliseconds()
}

class NewsResourceTypeConverter {
    @TypeConverter
    fun newsResourceTypeToString(value: com.example.nowinandroid_clone.core.model.data.NewsResourceType?): String? =
        value?.let(com.example.nowinandroid_clone.core.model.data.NewsResourceType::name)
    @TypeConverter
    fun stringToNewsResourceType(name: String?): com.example.nowinandroid_clone.core.model.data.NewsResourceType = when (name) {
        null -> com.example.nowinandroid_clone.core.model.data.NewsResourceType.Unknown
        else -> com.example.nowinandroid_clone.core.model.data.NewsResourceType.values()
            .firstOrNull { type -> type.name == name } ?: com.example.nowinandroid_clone.core.model.data.NewsResourceType.Unknown
    }
}