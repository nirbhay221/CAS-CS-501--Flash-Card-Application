package com.example.calculatorflashcardapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        var generateButton = findViewById<Button>(R.id.generateId)
        generateButton.setOnClickListener {

        }
    }
}