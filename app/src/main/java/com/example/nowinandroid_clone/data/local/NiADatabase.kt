package com.example.nowinandroid_clone.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.nowinandroid_clone.data.local.dao.AuthorDao
import com.example.nowinandroid_clone.data.local.dao.EpisodeDao
import com.example.nowinandroid_clone.data.local.dao.NewsResourceDao
import com.example.nowinandroid_clone.data.local.dao.TopicDao
import com.example.nowinandroid_clone.data.local.entities.*
import com.example.nowinandroid_clone.data.local.utilities.InstantConverter
import com.example.nowinandroid_clone.data.local.utilities.NewsResourceTypeConverter

@Database(
    entities = [
        AuthorEntity::class,
        NewsResourceEntity::class,
        TopicEntity::class,
        EpisodeEntity::class,
        EpisodeAuthorCrossRef::class,
        NewsResourceTopicCrossRef::class,
        NewsResourceAuthorCrossRef::class
    ],
    version = 1,
)
@TypeConverters(
    InstantConverter::class,
    NewsResourceTypeConverter::class
)
abstract class NiADatabase : RoomDatabase() {
    abstract fun topicDao(): TopicDao
    abstract fun authorDao(): AuthorDao
    abstract fun episodeDao(): EpisodeDao
    abstract fun newsResourceDao(): NewsResourceDao
}