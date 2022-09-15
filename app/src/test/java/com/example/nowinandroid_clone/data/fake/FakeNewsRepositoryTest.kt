package com.example.nowinandroid_clone.data.fake

import com.example.nowinandroid_clone.di.DefaultNiaDispatchers
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