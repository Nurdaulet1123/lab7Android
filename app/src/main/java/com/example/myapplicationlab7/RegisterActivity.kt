package com.example.myapplicationlab7

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {
    private lateinit var preferenceManager: PreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        preferenceManager = PreferenceManager(this)

        val etNewUsername = findViewById<EditText>(R.id.etNewUsername)
        val etNewPassword = findViewById<EditText>(R.id.etNewPassword)
        val btnRegister = findViewById<Button>(R.id.btnRegister)
        val btnBack = findViewById<Button>(R.id.btnBack)

        btnRegister.setOnClickListener {
            val username = etNewUsername.text.toString().trim()
            val password = etNewPassword.text.toString().trim()
            if (username.isNotEmpty() && password.isNotEmpty()) {
                preferenceManager.addUser(username, password)
                Toast.makeText(this, "User Registered", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }

        btnBack.setOnClickListener {
            finish()
        }
    }
}