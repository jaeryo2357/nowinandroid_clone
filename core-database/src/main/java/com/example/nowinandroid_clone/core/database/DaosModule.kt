package com.example.nowinandroid_clone.core.database

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {
    @Provides
    fun providesAuthorDao(
        database: com.example.nowinandroid_clone.core.database.NiADatabase
    ): com.example.nowinandroid_clone.core.database.dao.AuthorDao = database.authorDao()

    @Provides
    fun providesTopicsDao(
        database: com.example.nowinandroid_clone.core.database.NiADatabase,
    ): com.example.nowinandroid_clone.core.database.dao.TopicDao = database.topicDao()

    @Provides
    fun providesEpisodeDao(
        database: com.example.nowinandroid_clone.core.database.NiADatabase,
    ): com.example.nowinandroid_clone.core.database.dao.EpisodeDao = database.episodeDao()

    @Provides
    fun providesNewsResourceDao(
        database: com.example.nowinandroid_clone.core.database.NiADatabase,
    ): com.example.nowinandroid_clone.core.database.dao.NewsResourceDao = database.newsResourceDao()
}