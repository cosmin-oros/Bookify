package cosmin.dev.bookify.ui.screens.main

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import kotlin.random.Random

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MainScreen(navController: NavController) {
    var genres = remember { mutableStateListOf<String>() }
    val bookViewModel: BookViewModel = viewModel()
    var books by remember { mutableStateOf(emptyList<Book>()) }

    for (i in 0 until 20) {
        var gen = SharedPreferencesManager.getString("genre$i", "def")
        if (gen != "def") {
            genres.add(gen)
        }
    }

    fun fetchBooksByGenre() {
        // get 5 books for 5 different genres (if the user only has 3 selected then the genre will get repeated)
        for (i in 0 until 5) {
            var genreNr = Random.nextInt(20)
            var genre = genres[genreNr]

            bookViewModel.getBooksByGenre(genre) { fetchedBooks ->
                books = fetchedBooks
            }
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
                modifier = Modifier.size(200.dp),
            )

            Spacer(modifier = Modifier.height(32.dp))

        }
        
        Button(onClick = { fetchBooksByGenre() }) {
            
        }

        books?.forEach { book ->
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_launcher_foreground),
                    contentDescription = "Book Cover",
                    modifier = Modifier.size(100.dp)
                )
                Text(
                    text = book.title,
                    style = MaterialTheme.typography.subtitle1
                )
                Text(
                    text = book.author,
                    style = MaterialTheme.typography.caption
                )
                Text(
                    text = book.description,
                    style = MaterialTheme.typography.body2
                )
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