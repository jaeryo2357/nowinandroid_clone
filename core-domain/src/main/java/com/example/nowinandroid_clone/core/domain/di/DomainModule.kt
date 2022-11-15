package com.example.nowinandroid_clone.core.domain.di

import com.example.nowinandroid_clone.core.domain.repository.fake.FakeNewsRepository
import com.example.nowinandroid_clone.core.domain.repository.fake.FakeTopicsRepository
import com.example.nowinandroid_clone.core.domain.repository.NewsRepository
import com.example.nowinandroid_clone.core.domain.repository.TopicsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DomainModule {

    @Binds
    fun bindsTopicRepository(
        fakeTopicsRepository: FakeTopicsRepository
    ): TopicsRepository

    @Binds
    fun bindsNewsResourceRepository(
        fakeNewsRepository: FakeNewsRepository
    ): NewsRepository
}