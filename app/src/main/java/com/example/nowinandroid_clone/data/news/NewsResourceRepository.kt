package com.example.nowinandroid_clone.data.news

import kotlinx.coroutines.flow.Flow

interface NewsResourceRepository {
    fun monitor(): Flow<List<NewsResource>>
}