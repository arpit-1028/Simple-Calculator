//package com.example.calculatorapp
//
//import android.os.Bundle
//import android.widget.Button
//import android.widget.EditText
//import android.widget.TextView
//import android.widget.Toast
//import androidx.activity.enableEdgeToEdge
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.view.ViewCompat
//import androidx.core.view.WindowInsetsCompat
//import com.google.android.material.snackbar.Snackbar
//
//class MainActivity : AppCompatActivity() {
//    private var total = 0.0 // Store the cumulative total
//    private var multiplyTotal = 1.0
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        val inputField: EditText = findViewById(R.id.inputNumber)
//        val addButton: Button = findViewById(R.id.addButton)
//        val resultView: TextView = findViewById(R.id.resultView)
//        val clearButton : Button = findViewById(R.id.clearButton)
//        val multiplyButton:Button = findViewById(R.id.multiplyButton)
//        val subtractButton:Button = findViewById(R.id.subtractButton)
//        val divideButton:Button = findViewById(R.id.divideButton)
//
//
//
//
//        addButton.setOnClickListener {
//            val input = inputField.text.toString()
//            if (input.isNotEmpty()) {
//                val number = input.toDouble()
//                total += number
//                resultView.text = "Result: $total"
//                inputField.text.clear()
//            } else {
//                Toast.makeText(this, "Enter a number", Toast.LENGTH_SHORT).show()
//            }
//        }
//
//        subtractButton.setOnClickListener {
//            val input = inputField.text.toString()
//            if (input.isNotEmpty()) {
//                val number = input.toDouble()
//                total -= number
//                resultView.text = "Result: $total"
//                inputField.text.clear()
//            } else {
//                Toast.makeText(this, "Enter a number", Toast.LENGTH_SHORT).show()
//            }
//        }
//
//        divideButton.setOnClickListener {
//            val input = inputField.text.toString()
//            if (input.isNotEmpty()) {
//                val number = input.toDouble()
//                if (number==0.0) {
//                    Toast.makeText(this, "Enter a non zero number", Toast.LENGTH_SHORT).show()
//                }
//                else{
//                    total /= number
//                    resultView.text = "Result: $total"
//                    inputField.text.clear()
//                }
//            } else {
//                Toast.makeText(this, "Enter a number", Toast.LENGTH_SHORT).show()
//            }
//        }
//
//        multiplyButton.setOnClickListener {
//            val input = inputField.text.toString()
//            if (input.isNotEmpty()) {
//                val number = input.toDouble()
//                multiplyTotal *= number
//                resultView.text = "Result: $multiplyTotal"
//                inputField.text.clear()
//            } else {
//                Toast.makeText(this, "Enter a number", Toast.LENGTH_SHORT).show()
//            }
//        }
//
//        clearButton.setOnClickListener{
//            total=0.0
//            multiplyTotal = 1.0
//            inputField.text.clear()
//            resultView.text= "Result: $total"
//            Toast.makeText(this, "Cleared" ,Toast.LENGTH_SHORT).show()
//        }
//    }
//}
package com.example.calculatorapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private var total = 0.0
    private var multiplyTotal = 1.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputField: EditText = findViewById(R.id.inputNumber)
        val addButton: Button = findViewById(R.id.addButton)
        val subtractButton: Button = findViewById(R.id.subtractButton)
        val multiplyButton: Button = findViewById(R.id.multiplyButton)
        val divideButton: Button = findViewById(R.id.divideButton)
        val clearButton: Button = findViewById(R.id.clearButton)
        val resultView: TextView = findViewById(R.id.resultView)

        addButton.setOnClickListener { handleNumberInput(inputField, resultView) { number -> total += number } }
        subtractButton.setOnClickListener { handleNumberInput(inputField, resultView) { number -> total -= number } }
        multiplyButton.setOnClickListener { handleNumberInput(inputField, resultView) { number -> multiplyTotal *= number; total = multiplyTotal } }
        divideButton.setOnClickListener {
            handleNumberInput(inputField, resultView) { number ->
                if (number == 0.0) {
                    Snackbar.make(inputField, getString(R.string.enter_non_zero), Snackbar.LENGTH_SHORT).show()
                } else {
                    total /= number
                }
            }
        }
        clearButton.setOnClickListener {
            total = 0.0
            multiplyTotal = 1.0
            inputField.text.clear()
            resultView.text = getString(R.string.result, total)
            Snackbar.make(inputField, getString(R.string.cleared), Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun handleNumberInput(inputField: EditText, resultView: TextView, action: (Double) -> Unit) {
        val input = inputField.text.toString()
        val number = input.toDoubleOrNull()
        if (number != null) {
            action(number)
            inputField.text.clear()
            resultView.text = getString(R.string.result, total)
        } else {
            Snackbar.make(inputField, getString(R.string.enter_number), Snackbar.LENGTH_SHORT).show()
        }
    }
}
