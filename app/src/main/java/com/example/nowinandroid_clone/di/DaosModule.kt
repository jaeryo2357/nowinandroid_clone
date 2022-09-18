package com.example.nowinandroid_clone.di

import com.example.nowinandroid_clone.data.local.NiADatabase
import com.example.nowinandroid_clone.data.local.dao.AuthorDao
import com.example.nowinandroid_clone.data.local.dao.EpisodeDao
import com.example.nowinandroid_clone.data.local.dao.NewsResourceDao
import com.example.nowinandroid_clone.data.local.dao.TopicDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DaosModule {
    companion object {
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
}