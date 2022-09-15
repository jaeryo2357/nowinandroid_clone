package com.example.nowinandroid_clone.data.fake

import com.example.nowinandroid_clone.di.DefaultNiaDispatchers
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class FakeNiANetworkTest {
    private lateinit var subject: FakeNiaNetwork

    @Before
    fun setUp() {
        subject = FakeNiaNetwork(
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
}