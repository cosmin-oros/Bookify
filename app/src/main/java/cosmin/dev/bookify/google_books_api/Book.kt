package cosmin.dev.bookify.google_books_api

import kotlinx.serialization.Serializable

@Serializable
data class Book(
    val title: String,
    val author: String,
    val description: String
)