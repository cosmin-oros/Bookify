package cosmin.dev.bookify.navigation

const val MACROS_ARGUMENT_KEY = "id"

sealed class Screen(val route: String){
    object SplashScreen: Screen("splash_screen")
    object CongratulationsScreen: Screen("congrats_screen")
    object GenresScreen: Screen("genres_screen")
    object LengthPreferencesScreen: Screen("length_pref_screen")
    object LoginScreen: Screen("login_screen")
    object ReadingLevelScreen: Screen("reading_level_screen")
    object RegisterScreen: Screen("register_screen")
    object WelcomeScreen: Screen("welcome_screen")
    object MainScreen: Screen("main_screen")

    fun withArgs(vararg args: String): String{
        return buildString {
            append(route)
            args.forEach { arg->
                append("/$arg")
            }
        }
    }
}