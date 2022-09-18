package com.example.nowinandroid_clone.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.nowinandroid_clone.data.local.entities.NewsResourceEntity
import com.example.nowinandroid_clone.data.model.NewsResource
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