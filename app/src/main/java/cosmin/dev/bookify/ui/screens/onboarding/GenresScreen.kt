package cosmin.dev.bookify.ui.screens.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import cosmin.dev.bookify.R
import cosmin.dev.bookify.data.SharedPreferencesManager
import cosmin.dev.bookify.navigation.Screen

@Composable
fun GenresScreen(navController: NavController) {
    var genres = remember { mutableStateListOf("Fiction", "Mystery", "Thriller", "Romance", "Fantasy",
        "Science Fiction", "Historical Fiction", "Young Adult", "Contemporary", "Nonfiction", "Biography",
        "Memoir", "Self-Help", "Horror", "Crime", "Poetry", "Classics", "Paranormal", "Dystopian", "Humor") }
    var selectedGenres = remember { mutableStateListOf<String>() }
    var notEnoughGenresError = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(bottom = 32.dp), // Add bottom padding to push the content up
        verticalArrangement = Arrangement.SpaceBetween, // Use SpaceBetween to position elements at top and bottom
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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

//            Text(
//                text = "$first $last $agee",
//                style = MaterialTheme.typography.h6,
//                modifier = Modifier.padding(bottom = 16.dp)
//            )

            Text(
                text = "Please select your favorite genres! (Select at least 3)",
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(bottom = 16.dp),
                textAlign = TextAlign.Center,
            )

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(vertical = 8.dp)
            ) {
                for (i in 0 until genres.size) {
                    item {
                        Button(
                            onClick = {
                                if (!selectedGenres.contains(genres[i])) {
                                    selectedGenres.add(genres[i])
                                } else {
                                    selectedGenres.remove(genres[i])
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .shadow(4.dp, shape = MaterialTheme.shapes.medium)
                                .border(2.dp, Color.Cyan, shape = MaterialTheme.shapes.large),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = if (selectedGenres.contains(genres[i])) Color.Cyan else Color.Black
                            ),
                        ) {
                            Text(
                                text = genres[i],
                                style = MaterialTheme.typography.button.copy(
                                    color = Color.White,
                                    fontSize = 18.sp
                                )
                            )
                        }
                    }
                }

            }

        }

        if (notEnoughGenresError.value) {
            Text(
                text = "Please select at least 3 genres!",
                color = Color.Red,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        Button(
            onClick = {
                if (selectedGenres.size >= 3) {
                    notEnoughGenresError.value = false
                    // Set the preferences for genres
                    for (i in 0 until selectedGenres.size) {
                        SharedPreferencesManager.setString("genre${i + 1}", selectedGenres[i])
                    }
                    navController.navigate(Screen.LengthPreferencesScreen.route)
                } else {
                    notEnoughGenresError.value = true
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .height(50.dp)
                .shadow(4.dp, shape = MaterialTheme.shapes.medium)
                .border(2.dp, Color.Cyan, shape = MaterialTheme.shapes.large),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black),
        ) {
            Text(
                text = "Next",
                style = MaterialTheme.typography.button.copy(color = Color.White, fontSize = 18.sp)
            )
        }
    }
}