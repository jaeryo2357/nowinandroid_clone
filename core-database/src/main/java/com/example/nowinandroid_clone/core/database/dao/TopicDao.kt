package com.example.nowinandroid_clone.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.nowinandroid_clone.core.database.model.TopicEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TopicDao {
    @Query(value = "SELECT * FROM topics")
    fun getTopicEntitiesStream(): Flow<List<TopicEntity>>

    @Query(
        value = """
            SELECT * FROM topics
            WHERE id IN (:ids)
        """
    )
    fun getTopicEntitiesStream(ids: Set<Int>): Flow<List<TopicEntity>>

    @Insert
    suspend fun saveTopics(entities: List<TopicEntity>)
}