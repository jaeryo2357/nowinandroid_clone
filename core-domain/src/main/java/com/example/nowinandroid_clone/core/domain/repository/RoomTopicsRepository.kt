package com.example.nowinandroid_clone.core.domain.repository

import com.example.nowinandroid_clone.core.database.dao.TopicDao
import com.example.nowinandroid_clone.core.database.model.TopicEntity
import com.example.nowinandroid_clone.core.database.model.asExternalModel
import com.example.nowinandroid_clone.core.datastore.NiaPreferences
import com.example.nowinandroid_clone.core.domain.model.asEntity
import com.example.nowinandroid_clone.core.model.data.Topic
import com.example.nowinandroid_clone.core.network.NiANetwork
import com.example.nowinandroid_clone.core.network.NiaDispatchers
import com.example.nowinandroid_clone.core.network.model.NetworkTopic
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.concurrent.CancellationException
import javax.inject.Inject

class RoomTopicsRepository @Inject constructor(
    private val dispatchers: NiaDispatchers,
    private val topicDao: TopicDao,
    private val network: NiANetwork,
    private val niaPreferences: NiaPreferences
) : TopicsRepository {
    override fun getTopicsStream(): Flow<List<Topic>> =
        topicDao.getTopicEntitiesStream()
            .map { it.map(TopicEntity::asExternalModel) }

    override suspend fun setFollowedTopicIds(followedTopicIds: Set<Int>) =
        niaPreferences.setFollowedTopicIds(followedTopicIds)

    override suspend fun toggleFollowedTopicId(followedTopicId: Int, followed: Boolean) =
        niaPreferences.toggleFollowTopicId(followedTopicId, followed)

    override fun getFollowedTopicIdsStream(): Flow<Set<Int>> = niaPreferences.followedTopicIds

    override suspend fun sync(): Boolean = try {
        topicDao.saveTopics(
            network.getTopics()
                .map(NetworkTopic::asEntity)
        )
        true
    } catch (cancellationException: CancellationException) {
        throw cancellationException
    } catch (exception: Exception) {
        false
    }
}