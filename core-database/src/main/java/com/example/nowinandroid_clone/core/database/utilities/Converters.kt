package com.example.nowinandroid_clone.core.database.utilities

import androidx.room.TypeConverter
import com.example.nowinandroid_clone.core.model.data.NewsResourceType
import com.example.nowinandroid_clone.core.model.data.asNewsResourceType
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
    fun stringToNewsResourceType(name: String?): NewsResourceType =
        name.asNewsResourceType()
}