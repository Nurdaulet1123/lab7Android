package com.example.myapplicationlab7

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray
import org.json.JSONObject

class PreferenceManager(private val activity: AppCompatActivity) {
    private val sharedPref = activity.getPreferences(AppCompatActivity.MODE_PRIVATE)

    fun addUser(username: String, password: String) {
        val usersJson = sharedPref.getString("users", "[]")
        val usersArray = JSONArray(usersJson)

        val newUser = JSONObject().apply {
            put("username", username)
            put("password", password)
        }

        usersArray.put(newUser)

        with(sharedPref.edit()) {
            putString("users", usersArray.toString())
            apply()
        }
    }

    fun isValidUser(username: String, password: String): Boolean {
        val usersJson = sharedPref.getString("users", "[]")
        val usersArray = JSONArray(usersJson)

        for (i in 0 until usersArray.length()) {
            val user = usersArray.getJSONObject(i)
            if (user.getString("username") == username && user.getString("password") == password) {
                return true
            }
        }
        return false
    }

    fun clearUsers() {
        sharedPref.edit().clear().apply()
    }
}
