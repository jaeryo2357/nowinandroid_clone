package com.example.nowinandroid_clone.core.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideNiADatabase(
        @ApplicationContext context: Context
    ): com.example.nowinandroid_clone.core.database.NiADatabase = Room.databaseBuilder(
        context,
        com.example.nowinandroid_clone.core.database.NiADatabase::class.java,
        "nia-database"
    ).build()
}