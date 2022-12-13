package com.example.nowinandroid_clone.core.model.data

/**
 * A [NewsResource] with the additional information for whether it is saved.
 */
data class SaveableNewsResource(
    val newsResource: NewsResource,
    val isSaved: Boolean,
)