package com.example.nowinandroid_clone.data.fake

import com.example.nowinandroid_clone.data.model.NewsResource
import com.example.nowinandroid_clone.data.repository.NewsRepository
import com.example.nowinandroid_clone.di.NiaDispatchers
import kotlinx.coroutines.flow.*
import kotlinx.serialization.json.Json
import javax.inject.Inject

class FakeNewsRepository @Inject constructor(
    private val dispatcher: NiaDispatchers,
    private val networkJson: Json
) : NewsRepository {
    override fun getNewsResourcesStream(): Flow<List<NewsResource>> = flowOf(emptyList())

    override fun getNewsResourcesStream(filterTopicIds: Set<Int>): Flow<List<NewsResource>> =
        flowOf(emptyList())
}