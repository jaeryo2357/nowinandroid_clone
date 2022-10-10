package com.example.nowinandroid_clone.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.nowinandroid_clone.core.model.data.NewsResource
import com.example.nowinandroid_clone.core.model.entities.NewsResourceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsResourceDao {
    @Query(value = "SELECT * FROM news_resources")
    fun getNewsResourcesStream(): Flow<List<NewsResource>>

    @Query(
        value = """
            SELECT * FROM news_resources
            WHERE id IN(:filterTopicIds)
        """
    )
    fun getNewsResourcesStream(filterTopicIds: Set<Int>): Flow<List<NewsResource>>

    @Insert
    suspend fun saveNewsResourceEntities(entities: List<NewsResourceEntity>)
}