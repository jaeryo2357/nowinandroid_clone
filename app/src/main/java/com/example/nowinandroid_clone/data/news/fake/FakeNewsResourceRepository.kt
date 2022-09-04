package com.example.nowinandroid_clone.data.news.fake

import android.content.Context
import com.example.nowinandroid_clone.R
import com.example.nowinandroid_clone.data.news.NewsResource
import com.example.nowinandroid_clone.data.news.NewsResourceRepository
import kotlinx.coroutines.flow.Flow

class FakeNewsResourceRepository(
    private val context: Context
) : NewsResourceRepository {
    override fun monitor(): Flow<List<NewsResource>> {
        context.resources.openRawResource(R.raw.data)
        TODO("Deserialize json and return news resources")
    }
}