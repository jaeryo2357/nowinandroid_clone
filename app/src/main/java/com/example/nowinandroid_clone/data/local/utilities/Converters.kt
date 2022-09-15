package com.example.nowinandroid_clone.data.local.utilities

import androidx.room.TypeConverter
import com.example.nowinandroid_clone.data.model.NewsResourceType
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
    fun newsResourceTypeToString(value: NewsResourceType?): String? =
        value?.let(NewsResourceType::name)
    @TypeConverter
    fun stringToNewsResourceType(name: String?): NewsResourceType = when (name) {
        null -> NewsResourceType.Unknown
        else -> NewsResourceType.values()
            .firstOrNull { type -> type.name == name } ?: NewsResourceType.Unknown
    }
}