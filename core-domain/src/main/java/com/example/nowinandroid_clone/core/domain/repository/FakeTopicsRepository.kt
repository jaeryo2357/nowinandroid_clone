package com.example.nowinandroid_clone.core.domain.repository

import com.example.nowinandroid_clone.core.model.data.Topic
import com.example.nowinandroid_clone.core.datastore.NiaPreferences
import com.example.nowinandroid_clone.core.network.NiaDispatchers
import com.example.nowinandroid_clone.core.network.fake.FakeDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class FakeTopicsRepository @Inject constructor(
    private val dispatchers: NiaDispatchers,
    private val networkJson: Json,
    private val niaPreferences: NiaPreferences
) : TopicsRepository {
    override fun getTopicsStream(): Flow<List<Topic>> =
        flow {
          emit(networkJson.decodeFromString<List<Topic>>(
              FakeDataSource.topicsData).map {
              Topic(
                  id = it.id,
                  name = it.name,
                  description = it.description
              )
          })
        }.flowOn(dispatchers.IO)

    override suspend fun setFollowedTopicIds(followedTopicIds: Set<Int>) =
        niaPreferences.setFollowedTopicIds(followedTopicIds)

    override suspend fun toggleFollowedTopicId(followedTopicId: Int, followed: Boolean) =
        niaPreferences.toggleFollowTopicId(followedTopicId, followed)

    override fun getFollowedTopicIdsStream() =
        niaPreferences.followedTopicIds
}