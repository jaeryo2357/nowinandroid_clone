package com.example.nowinandroid_clone.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.nowinandroid_clone.data.local.entities.EpisodeEntity
import com.example.nowinandroid_clone.data.model.Episode
import kotlinx.coroutines.flow.Flow

@Dao
interface EpisodeDao {
    @Query(value = "SELECT * FROM episodes")
    fun getEpisodesStream(): Flow<List<Episode>>

    @Insert
    suspend fun saveEpisodeEntities(entities: List<EpisodeEntity>)
}