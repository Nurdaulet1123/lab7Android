package com.example.myapplicationlab7

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var preferenceManager: PreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        preferenceManager = PreferenceManager(this)

        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnSignup = findViewById<Button>(R.id.btnSignup)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnLogout = findViewById<Button>(R.id.btnLogout)

        btnSignup.setOnClickListener {
            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString().trim()
            if (username.isNotEmpty() && password.isNotEmpty()) {
                preferenceManager.saveUser(username, password)
                Toast.makeText(this, "User Registered", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }

        btnLogin.setOnClickListener {
            val (savedUsername, savedPassword) = preferenceManager.getUser()
            val inputUsername = etUsername.text.toString()
            val inputPassword = etPassword.text.toString()

            if (inputUsername == savedUsername && inputPassword == savedPassword) {
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show()
            }
        }

        btnLogout.setOnClickListener {
            preferenceManager.clearUser()
            Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show()
        }
    }
}
