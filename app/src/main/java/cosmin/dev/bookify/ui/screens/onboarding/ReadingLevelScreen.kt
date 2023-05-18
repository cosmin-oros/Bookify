package cosmin.dev.bookify.ui.screens.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import cosmin.dev.bookify.R
import cosmin.dev.bookify.data.SharedPreferencesManager
import cosmin.dev.bookify.navigation.Screen

@Composable
fun ReadingLevelScreen(navController: NavController) {
    var readingLevel by remember { mutableStateOf("occasional") }

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
                .fillMaxSize(0.9f)
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            Image(
                painter = painterResource(id = R.drawable.logo_falcon),
                contentDescription = "Logo",
                modifier = Modifier.size(200.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

//            Text(
//                text = "$first $last $agee",
//                style = MaterialTheme.typography.h6,
//                modifier = Modifier.padding(bottom = 16.dp)
//            )

            Text(
                text = "Please select your reading level!",
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(vertical = 8.dp)
            ) {
                item {
                    Button(
                        onClick = {
                            readingLevel = "occasional"
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .shadow(4.dp, shape = MaterialTheme.shapes.medium)
                            .border(2.dp, Color.Cyan, shape = MaterialTheme.shapes.large),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = if (readingLevel == "occasional") Color.Cyan else Color.Black
                        ),
                    ) {
                        Text(
                            text = "Occasional Reader",
                            style = MaterialTheme.typography.button.copy(color = Color.White, fontSize = 18.sp)
                        )
                    }
                }

                item {
                    Button(
                        onClick = {
                            readingLevel = "casual"
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .shadow(4.dp, shape = MaterialTheme.shapes.medium)
                            .border(2.dp, Color.Cyan, shape = MaterialTheme.shapes.large),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = if (readingLevel == "casual") Color.Cyan else Color.Black
                        ),
                    ) {
                        Text(
                            text = "Casual Reader",
                            style = MaterialTheme.typography.button.copy(color = Color.White, fontSize = 18.sp)
                        )
                    }
                }

                item {
                    Button(
                        onClick = {
                            readingLevel = "bookworm"
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .shadow(4.dp, shape = MaterialTheme.shapes.medium)
                            .border(2.dp, Color.Cyan, shape = MaterialTheme.shapes.large),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = if (readingLevel == "bookworm") Color.Cyan else Color.Black
                        ),
                    ) {
                        Text(
                            text = "Bookworm",
                            style = MaterialTheme.typography.button.copy(color = Color.White, fontSize = 18.sp)
                        )
                    }
                }

                item {
                    Button(
                        onClick = {
                            readingLevel = "bookaholic"
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .shadow(4.dp, shape = MaterialTheme.shapes.medium)
                            .border(2.dp, Color.Cyan, shape = MaterialTheme.shapes.large),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = if (readingLevel == "bookaholic") Color.Cyan else Color.Black
                        ),
                    ) {
                        Text(
                            text = "Bookaholic",
                            style = MaterialTheme.typography.button.copy(color = Color.White, fontSize = 18.sp)
                        )
                    }
                }
            }

        }

        Button(
            onClick = {
                // Set the preferences for reading level
                SharedPreferencesManager.setString("reading_level", readingLevel)
                navController.navigate(Screen.GenresScreen.route)
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