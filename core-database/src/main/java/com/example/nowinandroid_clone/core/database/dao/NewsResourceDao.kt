package com.example.nowinandroid_clone.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.nowinandroid_clone.core.database.model.NewsResourceEntity
import com.example.nowinandroid_clone.core.database.model.PopulatedNewsResource
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsResourceDao {
    @Query(value = "SELECT * FROM news_resources")
    fun getNewsResourcesStream(): Flow<List<PopulatedNewsResource>>

    @Query(
        value = """
            SELECT * FROM news_resources
            WHERE id IN(:filterTopicIds)
        """
    )
    fun getNewsResourcesStream(filterTopicIds: Set<Int>): Flow<List<PopulatedNewsResource>>

    @Insert
    suspend fun saveNewsResourceEntities(entities: List<NewsResourceEntity>)
}