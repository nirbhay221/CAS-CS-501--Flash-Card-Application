package com.example.calculatorflashcardapp

import androidx.lifecycle.SavedStateHandle
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }


    private lateinit var viewModel: FlashCardViewModel

    @Before
    fun setup() {
        val savedStateHandle = SavedStateHandle()
        viewModel = FlashCardViewModel(savedStateHandle)
    }

    @Test
    fun testProcessUserSubmissionCorrect() {
        viewModel.operand1 = 10
        viewModel.operand2 = 5
        viewModel.operator = "+"

        val isCorrect = viewModel.processUserSubmission("15")

        assertTrue(isCorrect)
        assertEquals(1, viewModel.score)
    }
    @Test
    fun testProcessUserSubmissionIncorrect() {
        viewModel.operand1 = 10
        viewModel.operand2 = 5
        viewModel.operator = "+"

        val isCorrect = viewModel.processUserSubmission("12")

        assertFalse(isCorrect)
        assertEquals(0, viewModel.score)
    }

    @Test
    fun testProcessUserSubmissionNull() {
        viewModel.operand1 = 11
        viewModel.operand2 = 6
        viewModel.operator = "-"

        val isCorrect = viewModel.processUserSubmission("")

        assertFalse(isCorrect)
        assertEquals(0, viewModel.score)
    }

    @Test
    fun testProcessUserSubmissionScoreUpdate() {
        viewModel.operand1 = 21
        viewModel.operand2 = 7
        viewModel.operator = "+"
        viewModel.score = 9

        val isCorrect = viewModel.processUserSubmission("28")

        assertTrue(isCorrect)
        assertEquals(10, viewModel.score)
    }




}