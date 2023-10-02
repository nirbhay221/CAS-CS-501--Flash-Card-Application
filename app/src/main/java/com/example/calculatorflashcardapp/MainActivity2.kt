package com.example.calculatorflashcardapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import kotlin.random.Random

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        var generateButton = findViewById<Button>(R.id.generateId)
        var operandId = findViewById<TextView>(R.id.operandId)
        var operand2Id = findViewById<TextView>(R.id.secondoperandId)
        var operatorId= findViewById<TextView>(R.id.operatorId)
        var answerId = findViewById<TextInputEditText>(R.id.answerId)
        var submitId = findViewById<Button>(R.id.answerSubmitId)
        var score = 0
        var totalProblems = 0

        generateButton.setOnClickListener {

            totalProblems = 10
            var operand1 = (1..99).random()
            operandId.text = operand1.toString()
            var operand2 = (1..20).random()
            operand2Id.text = operand2.toString()
            var operator = if (Random.nextBoolean()) "+" else "-"
            operatorId.text = operator
            Toast.makeText(this, "$operator", Toast.LENGTH_LONG).show()

            totalProblems--

        }

        submitId.setOnClickListener {



            val operand1 = operandId.text.toString().toInt()
            val operand2 = operand2Id.text.toString().toInt()
            val operator = operatorId.text.toString()

            val correctAnswer = if (operator == "+") operand1 + operand2 else operand1 - operand2
            val userAnswerText = answerId.text.toString()

            if (userAnswerText.isBlank()) {
                Toast.makeText(this, "Enter a value for the result", Toast.LENGTH_LONG).show()
            } else {
                val userAnswer = userAnswerText.toIntOrNull()
                if (userAnswer != null && userAnswer == correctAnswer) {
                    score++
                }
            }

            if (totalProblems == 0) {
                Toast.makeText(this, "Score: ${score}", Toast.LENGTH_LONG).show()
            }
            if (totalProblems > 0) {
                var operand1 = (1..99).random()
                operandId.text = operand1.toString()
                var operand2 = (1..20).random()
                operand2Id.text = operand2.toString()
                var operator = if (Random.nextBoolean()) "+" else "-"
                operatorId.text = operator
                Toast.makeText(this, "$operator", Toast.LENGTH_LONG).show()

                totalProblems--
            }
        }
    }

}
