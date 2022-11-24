package com.example.nowinandroid_clone.core.domain.repository

import com.example.nowinandroid_clone.core.domain.repository.fake.FakeNewsRepository
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.serialization.json.Json
import org.junit.Before

class FakeNewsRepositoryTest {
    private lateinit var subject:
            FakeNewsRepository

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        subject = FakeNewsRepository(
            ioDispatcher = testDispatcher,
            networkJson = Json {
                ignoreUnknownKeys = true
            }
        )
    }
}