package cosmin.dev.bookify.ui.screens.onboarding

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import cosmin.dev.bookify.R
import cosmin.dev.bookify.data.SharedPreferencesManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.material.ButtonDefaults
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import cosmin.dev.bookify.navigation.Screen

@Composable
fun WelcomeScreen(navController: NavController) {
//    val sharedPrefs = SharedPreferencesManager.getSharedPrefs()
//
//    // Read the preference value
//    val value = SharedPreferencesManager.getString("key", "default value")

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
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            Image(
                painter = painterResource(id = R.drawable.logo_falcon),
                contentDescription = "Logo",
                modifier = Modifier.size(200.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Bookify",
                style = MaterialTheme.typography.h2.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(bottom = 32.dp)
            )
        }

        Button(
            onClick = { navController.navigate(Screen.PersonalInfoScreen.route) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .height(50.dp)
                .shadow(4.dp, shape = MaterialTheme.shapes.medium)
                .border(2.dp, Color.Cyan, shape = MaterialTheme.shapes.large),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black),
        ) {
            Text(
                text = "Get Started",
                style = MaterialTheme.typography.button.copy(color = Color.White, fontSize = 18.sp)
            )
        }
    }
}