package cosmin.dev.bookify.ui.screens.onboarding

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import cosmin.dev.bookify.data.SharedPreferencesManager

@Composable
fun WelcomeScreen(navController: NavController) {
    val sharedPrefs = SharedPreferencesManager.getSharedPrefs()

    // Read the preference value
    val value = SharedPreferencesManager.getString("key", "default value")


}