package com.example.nowinandroid_clone.core.domain

import com.example.nowinandroid_clone.core.domain.repository.fake.FakeNewsRepository
import com.example.nowinandroid_clone.core.network.DefaultNiaDispatchers
import kotlinx.serialization.json.Json
import org.junit.Before

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
}