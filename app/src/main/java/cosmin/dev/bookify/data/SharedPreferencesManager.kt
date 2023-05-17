package cosmin.dev.bookify.data

import android.content.SharedPreferences

object SharedPreferencesManager {
    private lateinit var sharedPrefs: SharedPreferences

    fun initializeSharedPrefs(sharedPreferences: SharedPreferences) {
        sharedPrefs = sharedPreferences
    }

    fun getSharedPrefs(): SharedPreferences {
        return sharedPrefs
    }

    fun getString(key: String, defaultValue: String): String {
        return sharedPrefs.getString(key, defaultValue) ?: defaultValue
    }

    fun setString(key: String, value: String) {
        sharedPrefs.edit().putString(key, value).apply()
    }
}
