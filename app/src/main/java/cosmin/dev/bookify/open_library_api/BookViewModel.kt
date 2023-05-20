package cosmin.dev.bookify.open_library_api

import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException

data class BookResponse(
    val docs: List<BookItem>?
)

data class BookItem(
    val title: String?,
    val author_name: List<String>?,
    val description: String?,
    val cover_i: String?
)

class BookViewModel : ViewModel() {
    private val client = OkHttpClient()
    private val gson = Gson()

    fun getBooksByGenre(genre: String, callback: (List<Book>) -> Unit) {
        val encodedGenre = genre.replace(" ", "%20")
        val url = "https://openlibrary.org/subjects/$encodedGenre.json?limit=5"
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // Handle failure
            }

            override fun onResponse(call: Call, response: Response) {
                val responseData = response.body?.string()
                if (responseData != null) {
                    val bookResponse = gson.fromJson(responseData, BookResponse::class.java)
                    val books = bookResponse.docs?.mapNotNull { item ->
                        if (item.title != null && item.author_name != null) {
                            Book(
                                item.title,
                                item.author_name.joinToString(", "),
                                item.description ?: "",
                                "https://covers.openlibrary.org/b/id/${item.cover_i}-L.jpg".toInt()
                            )
                        } else {
                            null
                        }
                    } ?: emptyList()

                    callback(books)
                }
            }
        })
    }
}
