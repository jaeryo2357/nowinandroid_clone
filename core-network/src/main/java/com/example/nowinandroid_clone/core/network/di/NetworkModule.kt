package com.example.nowinandroid_clone.core.network.di

import com.example.nowinandroid_clone.core.network.NiANetwork
import com.example.nowinandroid_clone.core.network.fake.FakeNiANetwork
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NetworkModule {

    @Binds
    fun bindsNiANetwork(
        fakeNiANetwork: FakeNiANetwork
    ): NiANetwork

    companion object {
        @Provides
        @Singleton
        fun providesNetworkJson(): Json = Json {
            ignoreUnknownKeys = true
        }
    }
}