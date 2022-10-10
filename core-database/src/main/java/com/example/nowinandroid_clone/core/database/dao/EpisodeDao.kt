package com.example.nowinandroid_clone.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface EpisodeDao {
    @Query(value = "SELECT * FROM episodes")
    fun getEpisodesStream(): Flow<List<com.example.nowinandroid_clone.core.model.data.Episode>>

    @Insert
    suspend fun saveEpisodeEntities(entities: List<com.example.nowinandroid_clone.core.model.entities.EpisodeEntity>)
}