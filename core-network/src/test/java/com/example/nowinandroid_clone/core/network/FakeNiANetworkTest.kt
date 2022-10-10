package com.example.nowinandroid_clone.core.network

import com.example.nowinandroid_clone.core.network.fake.FakeDataSource
import com.example.nowinandroid_clone.core.network.fake.FakeNiANetwork
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class FakeNiANetworkTest {
    private lateinit var subject: FakeNiANetwork

    @Before
    fun setUp() {
        subject = FakeNiANetwork(
            dispatchers = DefaultNiaDispatchers(),
            networkJson = Json { ignoreUnknownKeys = true }
        )
    }

    @Test
    fun testDeserializationOfNewsResources() = runTest {
        assertEquals(
            FakeDataSource.sampleResource,
            subject.getNewsResources().first()
        )
    }

    @Test
    fun testDeserializationOfTopics() = runTest {
        assertEquals(
            FakeDataSource.sampleTopic,
            subject.getTopics().first()
        )
    }
}