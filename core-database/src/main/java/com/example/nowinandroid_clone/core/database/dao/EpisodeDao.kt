package com.example.nowinandroid_clone.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.nowinandroid_clone.core.database.model.EpisodeEntity
import com.example.nowinandroid_clone.core.database.model.PopulatedEpisode
import kotlinx.coroutines.flow.Flow

@Dao
interface EpisodeDao {
    @Query(value = "SELECT * FROM episodes")
    fun getEpisodesStream(): Flow<List<PopulatedEpisode>>

    @Insert
    suspend fun saveEpisodeEntities(entities: List<EpisodeEntity>)
}