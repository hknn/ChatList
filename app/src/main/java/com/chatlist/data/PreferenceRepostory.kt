package com.chatlist.data

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate

class PreferenceRepository(private val sharedPreferences: SharedPreferences) {

    var name: String? = ""
        get() = sharedPreferences.getString(PREFERENCE_NAME, "")
        set(value) {
            sharedPreferences.edit().putString(PREFERENCE_NAME, value).apply()
            field = value
        }

    companion object {
        private const val PREFERENCE_NAME = "preference_username"
    }
}