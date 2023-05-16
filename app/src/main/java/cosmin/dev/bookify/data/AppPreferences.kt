package cosmin.dev.bookify.data

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

@SuppressLint("StaticFieldLeak")
object AppPreferences {
    private lateinit var context: Context
    private val sharedPreferences by lazy {
        context.getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
    }

    fun initialize(context: Context) {
        this.context = context.applicationContext
    }

    var showTutorial by mutableStateOf(getBooleanPreference("showTutorial", true))
        private set

    @JvmName("setShowTutorial1")
    fun setShowTutorial(show: Boolean) {
        showTutorial = show
        saveBooleanPreference("showTutorial", show)
    }

    private fun getBooleanPreference(key: String, defaultValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, defaultValue)
    }

    private fun saveBooleanPreference(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }
}
