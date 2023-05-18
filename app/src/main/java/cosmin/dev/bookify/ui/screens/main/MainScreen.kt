package cosmin.dev.bookify.ui.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
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
fun MainScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
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