package com.example.nowinandroid_clone.core.database

import com.example.nowinandroid_clone.core.database.dao.AuthorDao
import com.example.nowinandroid_clone.core.database.dao.EpisodeDao
import com.example.nowinandroid_clone.core.database.dao.NewsResourceDao
import com.example.nowinandroid_clone.core.database.dao.TopicDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {
    @Provides
    fun providesAuthorDao(
        database: NiADatabase
    ): AuthorDao = database.authorDao()

    @Provides
    fun providesTopicsDao(
        database: NiADatabase,
    ): TopicDao = database.topicDao()

    @Provides
    fun providesEpisodeDao(
        database: NiADatabase,
    ): EpisodeDao = database.episodeDao()

    @Provides
    fun providesNewsResourceDao(
        database: NiADatabase,
    ): NewsResourceDao = database.newsResourceDao()
}