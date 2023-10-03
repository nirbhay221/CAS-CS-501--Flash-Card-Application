package com.example.calculatorflashcardapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import kotlin.random.Random

import androidx.activity.viewModels

class MainActivity2 : AppCompatActivity() {
    private lateinit var generateButton : Button
    private lateinit var operatorId : TextView
    private lateinit var operandId : TextView
    private lateinit var operand2Id : TextView
    private lateinit var answerId : TextInputEditText
    private lateinit var submitId : Button

    private val flashViewModel by viewModels<FlashCardViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        initializeViews()
        val previousTotalProblems = flashViewModel.totalProblems
        val previousScore = flashViewModel.score
        flashViewModel.score = 0
        flashViewModel.totalProblems = 0
        flashViewModel.additionCount = 0
        flashViewModel.subtractionCount = 0


        operandId.text = ""
        operand2Id.text = ""
        operatorId.text = ""
        setupGenerateButton()
        setupSubmitButton()

        operandId.text = flashViewModel.operand1.toString()
        operand2Id.text = flashViewModel.operand2.toString()
        operatorId.text = flashViewModel.operator.toString()
        flashViewModel.totalProblems = previousTotalProblems
        flashViewModel.score = previousScore
        if (!flashViewModel.isGenerateButtonVisible) {
            generateButton.visibility = Button.INVISIBLE
        }

    }
    private fun initializeViews(){
        generateButton = findViewById(R.id.generateId)
        operandId = findViewById(R.id.operandId)
        operand2Id = findViewById(R.id.secondoperandId)
        operatorId= findViewById(R.id.operatorId)
        answerId = findViewById(R.id.answerId)
        submitId = findViewById(R.id.answerSubmitId)
    }
    private fun setupGenerateButton() {
        generateButton.setOnClickListener {
            flashViewModel.generateProblems();
            flashViewModel.generateFlashCard()
            operandId.text = flashViewModel.operand1.toString()
            operand2Id.text = flashViewModel.operand2.toString()
            operatorId.text = flashViewModel.operator.toString()
            generateButton.visibility = Button.INVISIBLE
            flashViewModel.isGenerateButtonVisible = false
        }
    }
    private fun setupSubmitButton() {
        submitId.setOnClickListener {
            val userAnswerText = answerId.text.toString()
            val isCorrect = flashViewModel.processUserSubmission(userAnswerText)

            if (userAnswerText.isBlank()) {
                Toast.makeText(this, "Enter a value for the result", Toast.LENGTH_LONG).show()
            } else {
                if (isCorrect) {
                    // Handle correct answer
                } else {
                    // Handle incorrect answer
                }
                Toast.makeText(this, "Total Problems : ${flashViewModel.totalProblems}", Toast.LENGTH_LONG).show()

                if (flashViewModel.totalProblems >= 0) {
                    flashViewModel.generateFlashCard()
                    operandId.text = flashViewModel.operand1.toString()
                    operand2Id.text = flashViewModel.operand2.toString()
                    operatorId.text = flashViewModel.operator.toString()
                }
            }

            if (flashViewModel.totalProblems < 0) {
                operandId.text = ""
                operand2Id.text = ""
                operatorId.text = ""
                Toast.makeText(this, "Score: ${flashViewModel.score}", Toast.LENGTH_LONG).show()
                Toast.makeText(this, "Score: ${flashViewModel.score}", Toast.LENGTH_LONG).show()
                flashViewModel.score = 0
                flashViewModel.isGenerateButtonVisible=true
                generateButton.visibility = Button.VISIBLE
            }
        }
    }

}
