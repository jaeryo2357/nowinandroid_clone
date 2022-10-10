package com.example.nowinandroid_clone.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.nowinandroid_clone.core.database.dao.AuthorDao
import com.example.nowinandroid_clone.core.database.dao.EpisodeDao
import com.example.nowinandroid_clone.core.database.dao.NewsResourceDao
import com.example.nowinandroid_clone.core.database.dao.TopicDao
import com.example.nowinandroid_clone.core.database.utilities.InstantConverter
import com.example.nowinandroid_clone.core.database.utilities.NewsResourceTypeConverter
import com.example.nowinandroid_clone.core.model.entities.*

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