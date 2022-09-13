package com.example.nowinandroid_clone.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.example.nowinandroid_clone.data.UserPreferences
import com.example.nowinandroid_clone.data.UserPreferencesSerializer
import com.example.nowinandroid_clone.data.news.NewsRepository
import com.example.nowinandroid_clone.data.news.TopicsRepository
import com.example.nowinandroid_clone.data.news.fake.FakeNewsRepository
import com.example.nowinandroid_clone.data.news.fake.FakeTopicsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {

    @Binds
    fun bindsTopicRepository(fakeTopicsRepository: FakeTopicsRepository): TopicsRepository

    @Binds
    fun bindsNewsResourceRepository(
        fakeNewsRepository: FakeNewsRepository
    ): NewsRepository

    @Binds
    fun bindsNiaDispatchers(defaultNiaDispatchers: DefaultNiaDispatchers): NiaDispatchers

    companion object {
        @Provides
        @Singleton
        fun providesUserPreferencesDataStore(
            @ApplicationContext context: Context,
            userPreferencesSerializer: UserPreferencesSerializer
        ): DataStore<UserPreferences> =
            DataStoreFactory.create(
                serializer = userPreferencesSerializer
            ) {
                context.dataStoreFile("user_preferences.pb")
            }

        @Provides
        @Singleton
        fun provideNetworkJson(): Json = Json {
            ignoreUnknownKeys = true
        }
    }
}