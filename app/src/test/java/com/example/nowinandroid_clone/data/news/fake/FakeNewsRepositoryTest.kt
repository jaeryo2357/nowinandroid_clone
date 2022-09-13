package com.example.nowinandroid_clone.data.news.fake

import com.example.nowinandroid_clone.di.DefaultNiaDispatchers
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.Before
import org.junit.Test

class FakeNewsRepositoryTest {
    private lateinit var subject:
            FakeNewsRepository

    @Before
    fun setup() {
        subject = FakeNewsRepository(
            dispatcher = DefaultNiaDispatchers(),
            networkJson = Json {
                ignoreUnknownKeys = true
            }
        )
    }

    @Test
    fun testDeserializationOfNewsResources() = runTest {
        assertEquals(
            FakeDataSource.sampleResource,
            subject.getNewsResourcesStream().first().first()
        )
    }
}