package com.example.nowinandroid_clone.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.nowinandroid_clone.data.local.entities.AuthorEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AuthorDao {
    @Query(value = "SELECT * FROM authors")
    fun getAuthorStream(): Flow<List<AuthorEntity>>

    @Insert
    suspend fun suaveAuthorEntities(entities: List<AuthorEntity>)
}