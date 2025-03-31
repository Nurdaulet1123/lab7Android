package com.example.myapplicationlab7

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException

class FileActivity : AppCompatActivity() {
    private lateinit var tvData: TextView
    private val fileName = "test.txt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file)

        tvData = findViewById(R.id.tvData)
        val btnWrite = findViewById<Button>(R.id.btnWriteFile)
        val btnRead = findViewById<Button>(R.id.btnReadFile)
        val btnExit = findViewById<Button>(R.id.btnExit)

        btnWrite.setOnClickListener {
            val data = "Hello: ${java.util.Date()}"
            writeToFile(data)
        }

        btnRead.setOnClickListener {
            val data = readFromFile()
            tvData.text = data
        }

        btnExit.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun writeToFile(data: String) {
        val file = File(getExternalFilesDir(null), fileName)
        try {
            FileOutputStream(file).use {
                it.write(data.toByteArray())
                Toast.makeText(this, "Saved to ${file.absolutePath}", Toast.LENGTH_LONG).show()
            }
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(this, "Error writing file", Toast.LENGTH_SHORT).show()
        }
    }

    private fun readFromFile(): String {
        val file = File(getExternalFilesDir(null), fileName)
        return try {
            FileInputStream(file).bufferedReader().use { it.readText() }
        } catch (e: IOException) {
            e.printStackTrace()
            "Error reading file"
        }
    }
}