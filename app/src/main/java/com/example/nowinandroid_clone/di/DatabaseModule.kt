package com.example.nowinandroid_clone.di

import android.content.Context
import androidx.room.Room
import com.example.nowinandroid_clone.data.local.NiADatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DatabaseModule {
    companion object {
        @Provides
        @Singleton
        fun provideNiADatabase(
            @ApplicationContext context: Context
        ): NiADatabase = Room.databaseBuilder(
            context,
            NiADatabase::class.java,
            "nia-database"
        ).build()
    }
}