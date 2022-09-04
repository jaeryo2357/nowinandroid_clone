package com.example.nowinandroid_clone.data.news.fake

import android.content.Context
import android.content.res.Resources
import io.mockk.every
import io.mockk.mockk
import com.example.nowinandroid_clone.R
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class FakeNewsResourceRepositoryTest {
    private lateinit var subject:
            FakeNewsResourceRepository

    @Before
    fun setup() {
        val context = mockk<Context>()
        val resources = mockk<Resources>()
        every { resources.openRawResource(R.raw.data) } returns testResourcesJson.byteInputStream()
        every { context.resources } returns resources
    }

    @Test
    fun newsResources() = runBlocking {
        // TODO: Implement this
        // assertEquals(listOf<NewsResource>(), subject.monitor().first())
    }
}

private val testResourcesJson = """
        [
          {
            "episode": 52,
            "title": "MAD Skills: Paging Q&A",
            "content": "In this live session, TJ and Dustin answered your questions in the usual live Q&A format.",
            "URL": "https://youtu.be/8i6vrlbIVCc",
            "authorName": "",
            "publishDate": "2021-11-11T00:00:00.000Z",
            "type": "Video 📺",
            "topics": [
              "MAD Skills",
              "Paging"
            ],
            "alternateVideo": {
              "URL": "",
              "startTimestamp": "",
              "endTimestamp": ""
            }
          }
        ]
""".trimIndent()