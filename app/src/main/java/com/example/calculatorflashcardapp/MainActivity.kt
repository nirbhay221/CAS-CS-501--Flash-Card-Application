package com.example.calculatorflashcardapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var submitButton = findViewById<Button>(R.id.buttonId);
        var usernameId =  findViewById<TextInputEditText>(R.id.usernameId)
        var passwordId = findViewById<TextInputEditText>(R.id.passwordId)
        submitButton.setOnClickListener {
            var username =  findViewById<TextInputEditText>(R.id.usernameId).text.toString().trim();
            var password = findViewById<TextInputEditText>(R.id.passwordId).text.toString().trim();
            if(username.isEmpty()){
                usernameId.error = "Enter Username."
                usernameId.requestFocus()
            }
            else if(password.isEmpty()){
                passwordId.error = "Enter Password."
                passwordId.requestFocus()

            }else if(
                username == "admin" && password == "admin"
            ){

                val intent = Intent(this,MainActivity2::class.java)
                startActivity(intent)
                finish()
                showToast("Welcome, $username")

            }
            else {

                var messageTextView = findViewById<TextView>(R.id.messageId);
                val message = "Credentials are invalid. Try Again."
                messageTextView.text  = message
                usernameId.requestFocus()
                val handler = Handler();
                handler.postDelayed(
                    {
                        messageTextView.text = ""
                    },3000
                );

            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}