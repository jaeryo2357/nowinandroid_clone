package com.example.nowinandroid_clone.data.news.fake

import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Before
import org.junit.Test

class FakeNewsResourceRepositoryTest {
    private lateinit var subject:
            FakeNewsResourceRepository

    @Before
    fun setup() {
        subject = FakeNewsResourceRepository(
            ioDispatcher = TestCoroutineDispatcher()
        )
    }

    @Test
    fun testDeserializationOfNewsResources() = runBlocking {
        assertEquals(
            FakeDataSource.sampleResource,
            subject.monitor().first().first()
        )
    }
}