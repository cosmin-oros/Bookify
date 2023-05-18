package cosmin.dev.bookify.google_books_api

import android.net.http.HttpResponseCache.install
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.HttpClient
import io.ktor.client.request.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun fetchBooksByGenre(genre: String): List<Book> = withContext(Dispatchers.IO) {
    val httpClient = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }

    val url = "https://www.googleapis.com/books/v1/volumes"
    val response = httpClient.get<String>(url) {
        parameter("q", "subject:$genre")
        parameter("maxResults", "10") // Adjust the number of results as needed
    }

    val booksResponse = Json.decodeFromString<BooksResponse>(response)
    booksResponse.items.map { book ->
        Book(
            book.volumeInfo.title,
            book.volumeInfo.authors?.joinToString(", ") ?: "Unknown Author",
            book.volumeInfo.description ?: ""
        )
    }
}

@Serializable
data class BooksResponse(
    val items: List<VolumeItem>
)

@Serializable
data class VolumeItem(
    val volumeInfo: VolumeInfo
)

@Serializable
data class VolumeInfo(
    val title: String,
    val authors: List<String>?,
    val description: String?
)
