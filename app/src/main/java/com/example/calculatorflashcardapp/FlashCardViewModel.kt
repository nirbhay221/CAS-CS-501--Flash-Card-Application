package com.example.calculatorflashcardapp

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlin.random.Random


const val CURRENT_SCORE_KEY = "CURRENT_SCORE_KEY"
const val CURRENT_OPERAND1_KEY = "CURRENT_OPERAND1_KEY"
const val CURRENT_OPERAND2_KEY = "CURRENT_OPERAND2_KEY"
const val CURRENT_OPERATOR_KEY = "CURRENT_OPERATOR_KEY"
const val CURRENT_USERANSWERTEXT_KEY = "CURRENT_USERANSWERTEXT_KEY"
const val CURRENT_USERANSWER_KEY = "CURRENT_USERANSWER_KEY"
const val CURRENT_TOTALANSWER_KEY = "CURRENT_TOTALANSWER_KEY"

const val CURRENT_ADDITIONCOUNT_KEY = "CURRENT_ADDITIONCOUNT_KEY"

const val CURRENT_SUBTRACTIONCOUNT_KEY = "CURRENT_SUBTRACTIONCOUNT_KEY"

const val CURRENT_TOTALPROBLEMS_KEY = "CURRENT_TOTALPROBLEMS_KEY"
class FlashCardViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    var score: Int
    get() = savedStateHandle.get(CURRENT_SCORE_KEY) ?: 0
    set(value) = savedStateHandle.set(CURRENT_SCORE_KEY, value)
    var isGenerateButtonVisible: Boolean
        get() = savedStateHandle.get(CURRENT_GENERATE_BUTTON_VISIBLE_KEY) ?: true
        set(value) = savedStateHandle.set(CURRENT_GENERATE_BUTTON_VISIBLE_KEY, value)

    companion object {
        private const val CURRENT_GENERATE_BUTTON_VISIBLE_KEY = "CURRENT_GENERATE_BUTTON_VISIBLE_KEY"
    }
    var operand1: Int
        get() = savedStateHandle.get(CURRENT_OPERAND1_KEY) ?: 0
        set(value) = savedStateHandle.set(CURRENT_OPERAND1_KEY, value)
    var operand2: Int
        get() = savedStateHandle.get(CURRENT_OPERAND2_KEY) ?: 0
        set(value) = savedStateHandle.set(CURRENT_OPERAND2_KEY, value)
    var operator: String
        get() = (savedStateHandle.get(CURRENT_OPERATOR_KEY) ?: "")
        set(value) = savedStateHandle.set(CURRENT_OPERATOR_KEY, value)
    var userAnswerText: String
        get() = savedStateHandle.get(CURRENT_USERANSWERTEXT_KEY) ?: ""
        set(value) = savedStateHandle.set(CURRENT_USERANSWERTEXT_KEY, value)
    var userAnswer :Double
        get() = savedStateHandle.get(CURRENT_USERANSWER_KEY) ?: 0.0
        set(value) = savedStateHandle.set(CURRENT_USERANSWER_KEY, value)
    var totalAnswers: Int
        get() = savedStateHandle.get(CURRENT_TOTALANSWER_KEY) ?: 0
        set(value) = savedStateHandle.set(CURRENT_TOTALANSWER_KEY, value)
    var additionCount : Int
        get() = savedStateHandle.get(CURRENT_ADDITIONCOUNT_KEY) ?: 0
        set(value) = savedStateHandle.set(CURRENT_ADDITIONCOUNT_KEY, value)
    var subtractionCount : Int
        get() = savedStateHandle.get(CURRENT_SUBTRACTIONCOUNT_KEY) ?: 0
        set(value) = savedStateHandle.set(CURRENT_SUBTRACTIONCOUNT_KEY, value)
    var totalProblems : Int
        get() = savedStateHandle.get(CURRENT_TOTALPROBLEMS_KEY) ?: 0
        set(value) = savedStateHandle.set(CURRENT_TOTALPROBLEMS_KEY, value)

    fun generateProblems(){
        this.totalProblems= 10;
    }
    fun generateFlashCard() {
        operand1 = (1..99).random()
        operand2 = (1..20).random()
        operator = when {
            additionCount < 5 && subtractionCount < 5 -> {
                if (Random.nextBoolean()) {
                    additionCount++
                    "+"
                } else {
                    subtractionCount++
                    "-"
                }
            }
            additionCount == 5 && subtractionCount < 5 -> {
                subtractionCount++
                "-"
            }
            additionCount < 5 && subtractionCount == 5 -> {
                additionCount++
                "+"
            }
            else -> {
                additionCount = 0
                subtractionCount = 0
                if (Random.nextBoolean()) {
                    additionCount++
                    "+"
                } else {
                    subtractionCount++
                    "-"
                }
            }
        }
        this.totalProblems--;
    }
    fun processUserSubmission(userAnswerText: String): Boolean {
        val correctAnswer = if (operator == "+") operand1 + operand2 else operand1 - operand2
        this.userAnswerText = userAnswerText

        if (userAnswerText.isBlank()) {
            return false
        }
        userAnswerText.toIntOrNull()?.let { userAnswerInt ->
            userAnswer = userAnswerInt.toDouble()
            if (userAnswerInt == correctAnswer) {
                score++
                return true
            }
        }
        return false
    }
}