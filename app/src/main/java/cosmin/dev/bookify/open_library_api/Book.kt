package cosmin.dev.bookify.open_library_api

import kotlinx.serialization.Serializable

@Serializable
data class Book(
    val title: String,
    val author: String,
    val description: String,
    val imageUrl: Int,
)