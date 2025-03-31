package com.example.myapplicationlab7

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity

class PreferenceManager(private val activity: AppCompatActivity) {
    private val sharedPref = activity.getPreferences(AppCompatActivity.MODE_PRIVATE)

    fun saveUser(username: String, password: String) {
        with(sharedPref.edit()) {
            putString("username", username)
            putString("password", password)
            apply()
        }
    }

    fun getUser(): Pair<String?, String?> {
        val username = sharedPref.getString("username", null)
        val password = sharedPref.getString("password", null)
        return Pair(username, password)
    }

    fun clearUser() {
        sharedPref.edit().clear().apply()
    }
}