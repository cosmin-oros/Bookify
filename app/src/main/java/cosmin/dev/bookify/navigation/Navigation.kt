package cosmin.dev.bookify.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import cosmin.dev.bookify.ui.screens.main.MainScreen
import cosmin.dev.bookify.ui.screens.onboarding.*

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.SplashScreen.route){
        composable(Screen.SplashScreen.route) {
            SplashScreen(navController = navController)
        }

        composable(Screen.WelcomeScreen.route) {
            WelcomeScreen(navController = navController)
        }

        composable(Screen.RegisterScreen.route) {
            RegisterScreen(navController = navController)
        }

        composable(Screen.LoginScreen.route) {
            LoginScreen(navController = navController)
        }

        composable(Screen.ReadingLevelScreen.route) {
            ReadingLevelScreen(navController = navController)
        }

        composable(Screen.GenresScreen.route) {
            GenresScreen(navController = navController)
        }

        composable(Screen.LengthPreferencesScreen.route) {
            LengthPreferenceScreen(navController = navController)
        }


        composable(Screen.CongratulationsScreen.route) {
            CongratulationsScreen(navController = navController)
        }

        composable(Screen.MainScreen.route) {
            MainScreen(navController = navController)
        }

    }
}