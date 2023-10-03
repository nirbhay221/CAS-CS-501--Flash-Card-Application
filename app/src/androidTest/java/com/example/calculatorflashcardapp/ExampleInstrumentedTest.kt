package com.example.calculatorflashcardapp

import android.content.pm.ActivityInfo
import androidx.lifecycle.SavedStateHandle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions

import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import androidx.test.espresso.matcher.ViewMatchers.withId

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    private lateinit var scenario: ActivityScenario<MainActivity>
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.calculatorflashcardapp", appContext.packageName)
    }

    @Test
    fun anotherTest() {
        scenario = launch(MainActivity::class.java)

        Espresso.onView(withId(R.id.usernameId)).perform(ViewActions.typeText("admin"))
        Espresso.onView(withId(R.id.passwordId)).perform(ViewActions.typeText("admin"))
        Espresso.onView(withId(R.id.buttonId)).perform(ViewActions.click())

        val savedStateHandle = SavedStateHandle(mapOf(CURRENT_TOTALPROBLEMS_KEY to 10))
        val flashCardViewModel = FlashCardViewModel(savedStateHandle)

        Espresso.onView(withId(R.id.generateId)).perform(ViewActions.click())
        var answer = 0
        for (i in 1..10) {
            Espresso.onView(withId(R.id.answerId))
                .perform(ViewActions.typeText("0"))
                .perform(ViewActions.closeSoftKeyboard())
            Espresso.onView(withId(R.id.answerSubmitId)).perform(ViewActions.click())
        }
        Espresso.onView(withId(R.id.generateId))
            .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))

    }

    @Test
    fun anotherTest2() {
        scenario = launch(MainActivity::class.java)

        Espresso.onView(withId(R.id.usernameId)).perform(ViewActions.typeText("admin"))
        Espresso.onView(withId(R.id.passwordId)).perform(ViewActions.typeText("admin"))
        Espresso.onView(withId(R.id.buttonId)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.generateId)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.generateId))
            .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)))

    }





}