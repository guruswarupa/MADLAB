package com.guruswarupa.calci

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var tvDisplay: TextView
    private var firstValue = 0.0
    private var currentOperator: String? = null
    private var isStartingNewNumber = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Makes the app full-screen
        setContentView(R.layout.activity_main)

        tvDisplay = findViewById(R.id.tvDisplay)

        // 1. Setup Digit Buttons (0-9 and .)
        val digitButtons = listOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
            R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9, R.id.btnDot
        )
        for (id in digitButtons) {
            findViewById<Button>(id).setOnClickListener { 
                appendNumber((it as Button).text.toString())
            }
        }

        // 2. Setup Operator Buttons (+, -, *, /, %)
        val operators = mapOf(
            R.id.btnAdd to "+", R.id.btnSub to "-", 
            R.id.btnMul to "*", R.id.btnDiv to "/", R.id.btnMod to "%"
        )
        for ((id, op) in operators) {
            findViewById<Button>(id).setOnClickListener { prepareOperation(op) }
        }

        // 3. Action Buttons (Clear, Delete, Equals)
        findViewById<Button>(R.id.btnC).setOnClickListener { clearAll() }
        findViewById<Button>(R.id.btnDel).setOnClickListener { deleteLastDigit() }
        findViewById<Button>(R.id.btnEqual).setOnClickListener { calculateResult() }
    }

    // Adds a digit to the display
    private fun appendNumber(num: String) {
        if (isStartingNewNumber) {
            tvDisplay.text = if (num == ".") "0." else num
            isStartingNewNumber = false
        } else {
            val current = tvDisplay.text.toString()
            if (num == "." && current.contains(".")) return // Prevent double decimals
            tvDisplay.text = current + num
        }
    }

    // Saves the first number and the operator
    private fun prepareOperation(op: String) {
        firstValue = tvDisplay.text.toString().toDoubleOrNull() ?: 0.0
        currentOperator = op
        isStartingNewNumber = true
    }

    // Performs the calculation
    private fun calculateResult() {
        val secondValue = tvDisplay.text.toString().toDoubleOrNull() ?: 0.0
        val result = when (currentOperator) {
            "+" -> firstValue + secondValue
            "-" -> firstValue - secondValue
            "*" -> firstValue * secondValue
            "/" -> if (secondValue != 0.0) firstValue / secondValue else Double.NaN
            "%" -> firstValue % secondValue
            else -> secondValue
        }

        // Format result: Remove .0 if it's a whole number
        tvDisplay.text = if (result % 1 == 0.0 && !result.isNaN()) {
            result.toLong().toString()
        } else {
            result.toString()
        }
        
        isStartingNewNumber = true
        currentOperator = null
    }

    private fun clearAll() {
        tvDisplay.text = "0"
        firstValue = 0.0
        currentOperator = null
        isStartingNewNumber = true
    }

    private fun deleteLastDigit() {
        val current = tvDisplay.text.toString()
        if (current.length > 1) {
            tvDisplay.text = current.dropLast(1)
        } else {
            tvDisplay.text = "0"
            isStartingNewNumber = true
        }
    }
}