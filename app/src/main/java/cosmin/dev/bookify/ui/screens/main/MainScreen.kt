package cosmin.dev.bookify.ui.screens.main

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import cosmin.dev.bookify.R
import cosmin.dev.bookify.data.SharedPreferencesManager
import cosmin.dev.bookify.open_library_api.Book
import cosmin.dev.bookify.open_library_api.BookViewModel
import cosmin.dev.bookify.navigation.Screen
import cosmin.dev.bookify.open_library_api.BookImage
import cosmin.dev.bookify.open_library_api.DefaultBooks
import kotlin.random.Random

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MainScreen(navController: NavController) {
    var genres = remember { mutableStateListOf<String>() }
    val bookViewModel: BookViewModel = viewModel()
    var books by remember { mutableStateOf<ArrayList<Book>>(DefaultBooks.getBooks()) }
    var fetchBooks by remember { mutableStateOf(emptyList<Book>()) }

    for (i in 0 until 20) {
        var gen = SharedPreferencesManager.getString("genre${i+1}", "def")
        if (gen != "def") {
            genres.add(gen)
        }
    }

    fun fetchBooksByGenre() {
        // get 5 books for 5 different genres (if the user only has 3 selected then the genre will get repeated)
        for (i in 0 until 5) {
            var genreNr = Random.nextInt(genres.size)
            var genre = genres[genreNr]

            bookViewModel.getBooksByGenre(genre) { fetchedBooks ->
                fetchBooks = fetchedBooks
            }
        }

        if (fetchBooks.isNotEmpty()) {
            books = fetchBooks as ArrayList<Book>
        }
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.SpaceBetween, // Use SpaceBetween to position elements at top and bottom
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//        for (i in 0 until 5) {
//            Text(text = books[i].title)
//        }
        Column(
            verticalArrangement = Arrangement.Center, // Center the top content vertically
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize(0.85f)
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            Image(
                painter = painterResource(id = R.drawable.logo_falcon),
                contentDescription = "Logo",
                modifier = Modifier.size(200.dp).clickable { fetchBooks },
            )

            Spacer(modifier = Modifier.height(32.dp))

            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(vertical = 8.dp)
            ) {
                for (i in 0 until 5) {
                    item {
                        Card(
                            modifier = Modifier
                                .padding(horizontal = 8.dp, vertical = 8.dp)
                                .fillMaxWidth(),
                            elevation = 2.dp,
                            backgroundColor = Color.DarkGray,
                            shape = RoundedCornerShape(corner = CornerSize(16.dp)),
                        ) {
                            Row {
                                BookImage(book = books[i])

                                Column(
                                    modifier = Modifier
                                        .padding(16.dp)
                                        .fillMaxWidth()
                                        .align(Alignment.CenterVertically)
                                ) {
                                    Text(text = books[i].title, style = MaterialTheme.typography.h5)
                                    Text(
                                        text = books[i].author,
                                        style = MaterialTheme.typography.caption
                                    )

//                                    Text(
//                                        text = books[i].description,
//                                        style = MaterialTheme.typography.h6,
//                                        color = Color.Green
//                                    )

                                }

                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }

        }

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Change your info",
                color = Color.Cyan,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(top = 4.dp)
            )

            Box(
                modifier = Modifier.clickable {
                    SharedPreferencesManager.setString("logged_in", "no")
                    navController.navigate(Screen.WelcomeScreen.route)
                },
            ) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Refresh Icon",
                    tint = Color.Cyan,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }

}