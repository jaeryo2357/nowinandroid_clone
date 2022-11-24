package com.example.nowinandroid_clone.core.testing.di

import com.example.nowinandroid_clone.core.common.network.Dispatcher
import com.example.nowinandroid_clone.core.common.network.NiaDispatchers
import com.example.nowinandroid_clone.core.common.network.di.DispatchersModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DispatchersModule::class]
)
object TestDispatchersModule {
    @Provides
    @Dispatcher(NiaDispatchers.IO)
    fun providesIODispatcher(testDispatcher: TestDispatcher) =
        testDispatcher
}