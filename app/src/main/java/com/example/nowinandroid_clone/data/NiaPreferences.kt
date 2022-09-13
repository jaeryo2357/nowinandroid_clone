package com.example.nowinandroid_clone.data

import android.util.Log
import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.retry
import java.io.IOException
import javax.inject.Inject

class NiaPreferences @Inject constructor(
    private val userPreferences: DataStore<UserPreferences>
) {
    suspend fun setFollowedTopicIds(followedTopicIds: Set<Int>) {
        try {
            userPreferences.updateData {
                it.copy {
                    this.followedTopicIds.clear()
                    this.followedTopicIds.addAll(followedTopicIds)
                }
            }
        } catch (ioException: IOException) {
            Log.e("NiaPreferences", "Failed to update user preferences", ioException)
        }
    }

    val followedTopicIds: Flow<Set<Int>> =
        userPreferences.data
            .retry {
                Log.e("NiaPreferences", "Failed to read user preferences", it)
                true
            }
            .map {
                it.followedTopicIdsList.toSet()
            }
}