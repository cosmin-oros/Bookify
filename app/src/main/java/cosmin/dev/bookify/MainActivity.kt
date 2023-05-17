package cosmin.dev.bookify

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import androidx.preference.PreferenceManager
import cosmin.dev.bookify.data.SharedPreferencesManager
import cosmin.dev.bookify.navigation.Navigation
import cosmin.dev.bookify.ui.theme.BookifyTheme

class MainActivity : ComponentActivity() {
    private lateinit var sharedPrefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        SharedPreferencesManager.initializeSharedPrefs(sharedPrefs)
        setContent {
            BookifyTheme {
                val navController = rememberNavController()

                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ){
                    Navigation(navController = navController)
                }
            }
        }
    }
}


