package com.example.myapplicationlab7

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)

    fun saveUser(username: String, password: String) {
        prefs.edit().putString("username", username).putString("password", password).apply()
    }

    fun getUser(): Pair<String?, String?> {
        return Pair(prefs.getString("username", null), prefs.getString("password", null))
    }
}