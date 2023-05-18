package cosmin.dev.bookify.ui.screens.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Help
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
fun LengthPreferenceScreen(navController: NavController) {
    var lenPreference by remember { mutableStateOf("short") }
    var isIconPressed by remember { mutableStateOf(false) }

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
                .fillMaxSize(0.75f)
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
                text = "Please select your book length preference!",
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
                item {
                    Button(
                        onClick = {
                            lenPreference = "short"
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .shadow(4.dp, shape = MaterialTheme.shapes.medium)
                            .border(2.dp, Color.Cyan, shape = MaterialTheme.shapes.large),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = if (lenPreference == "short") Color.Cyan else Color.Black
                        ),
                    ) {
                        Text(
                            text = "Short",
                            style = MaterialTheme.typography.button.copy(color = Color.White, fontSize = 18.sp)
                        )
                    }
                }

                item {
                    Button(
                        onClick = {
                            lenPreference = "medium"
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .shadow(4.dp, shape = MaterialTheme.shapes.medium)
                            .border(2.dp, Color.Cyan, shape = MaterialTheme.shapes.large),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = if (lenPreference == "medium") Color.Cyan else Color.Black
                        ),
                    ) {
                        Text(
                            text = "Medium",
                            style = MaterialTheme.typography.button.copy(color = Color.White, fontSize = 18.sp)
                        )
                    }
                }

                item {
                    Button(
                        onClick = {
                            lenPreference = "long"
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .shadow(4.dp, shape = MaterialTheme.shapes.medium)
                            .border(2.dp, Color.Cyan, shape = MaterialTheme.shapes.large),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = if (lenPreference == "long") Color.Cyan else Color.Black
                        ),
                    ) {
                        Text(
                            text = "Long",
                            style = MaterialTheme.typography.button.copy(color = Color.White, fontSize = 18.sp)
                        )
                    }
                }

            }

        }

        Box(
            modifier = Modifier.clickable { isIconPressed = !isIconPressed },
        ) {
            Icon(
                imageVector = Icons.Default.Help,
                contentDescription = "Question Icon",
                tint = if (isIconPressed) Color.White else Color.Cyan,
                modifier = Modifier.size(24.dp)
            )
        }

        if (isIconPressed) {
            Text(
                text = "Short - less than 100 pages",
                color = Color.White,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(top = 4.dp)
            )
            Text(
                text = "Medium - between 100 and 400 pages",
                color = Color.White,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(top = 4.dp)
            )
            Text(
                text = "Long - more than 400 pages",
                color = Color.White,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        Button(
            onClick = {
                // Set the preferences for reading level
                SharedPreferencesManager.setString("len_preference", lenPreference)
                navController.navigate(Screen.CongratulationsScreen.route)
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