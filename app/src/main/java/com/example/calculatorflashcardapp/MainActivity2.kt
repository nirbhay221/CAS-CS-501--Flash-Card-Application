package com.example.calculatorflashcardapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import java.util.Random

class MainActivity2 : AppCompatActivity() {

//    val op1 = listOf<Int>(56,34,1,6,79,84)
//    val op2 = listOf<Int>(34,67,23,19,54,56)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

//        var answer =  findViewById<TextInputEditText>(R.id.answer).text.toString().trim()
        var submitButton = findViewById<Button>(R.id.submit)
        var operand1 =  findViewById<TextView>(R.id.operand1)
        var operator =  findViewById<TextView>(R.id.operator)
        var operand2 =  findViewById<TextView>(R.id.operand2)
        var rand = Random()
        var op1 = 0
        var op2 = 0
        var opa = 1

//        genQuestion()

        op1 = rand.nextInt(99)
        op2 = rand.nextInt(99)
        opa = rand.nextInt(2)

        operand1.text = op1.toString()
        if (opa==1){
            operator.setText(R.string.add)
        }else{
            operator.setText(R.string.subtract)
        }
        operand2.text = op2.toString()

        submitButton.setOnClickListener{
            var answer =  findViewById<TextInputEditText>(R.id.answer).text.toString().toInt()
            var ans =  when{
                (operator.text.toString()=="+") -> operand1.text.toString().toInt()+operand2.text.toString().toInt()
                else -> operand1.text.toString().toInt()-operand2.text.toString().toInt()
            }
            val messageResId = when (ans) {
                answer -> R.string.correct
                else -> R.string.incorrect
            }

            Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
                .show()

            var op3 = rand.nextInt(99)
            var op4 = rand.nextInt(99)
            var opb = rand.nextInt(2)

            operand1.text = op3.toString()
//            operator.setText(R.string.add)
            if (opb==1){
                operator.setText(R.string.add)
            }else{
                operator.setText(R.string.subtract)
            }
            operand2.text = op4.toString()
        }
    }

//    fun genQuestion(){
//        var op1 = rand.nextInt(99)
//        var op2 = rand.nextInt(99)
//        operand1.text = op1.toString()
//        operator.setText(R.string.add)
//        operand2.text = op2.toString()
//    }

}