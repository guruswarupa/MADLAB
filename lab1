package com.guruswarupa.calci

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var tvDisplay: TextView
    private var firstOperand: Double = 0.0
    private var operator: String? = null
    private var isNewOp = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        tvDisplay = findViewById(R.id.tvDisplay)

        val buttons = listOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
            R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9, R.id.btnDot
        )

        for (id in buttons) {
            findViewById<Button>(id).setOnClickListener { onDigitClick((it as Button).text.toString()) }
        }

        findViewById<Button>(R.id.btnAdd).setOnClickListener { onOperatorClick("+") }
        findViewById<Button>(R.id.btnSub).setOnClickListener { onOperatorClick("-") }
        findViewById<Button>(R.id.btnMul).setOnClickListener { onOperatorClick("*") }
        findViewById<Button>(R.id.btnDiv).setOnClickListener { onOperatorClick("/") }
        findViewById<Button>(R.id.btnMod).setOnClickListener { onOperatorClick("%") }

        findViewById<Button>(R.id.btnC).setOnClickListener {
            tvDisplay.text = "0"
            firstOperand = 0.0
            operator = null
            isNewOp = true
        }

        findViewById<Button>(R.id.btnDel).setOnClickListener {
            val current = tvDisplay.text.toString()
            if (current.length > 1) {
                tvDisplay.text = current.substring(0, current.length - 1)
            } else {
                tvDisplay.text = "0"
            }
        }

        findViewById<Button>(R.id.btnEqual).setOnClickListener { calculate() }
    }

    private fun onDigitClick(digit: String) {
        if (isNewOp) {
            tvDisplay.text = ""
            isNewOp = false
        }
        val current = tvDisplay.text.toString()
        if (digit == "." && current.contains(".")) return
        tvDisplay.text = current + digit
    }

    private fun onOperatorClick(op: String) {
        firstOperand = tvDisplay.text.toString().toDoubleOrNull() ?: 0.0
        operator = op
        isNewOp = true
    }

    private fun calculate() {
        val secondOperand = tvDisplay.text.toString().toDoubleOrNull() ?: 0.0
        val result = when (operator) {
            "+" -> firstOperand + secondOperand
            "-" -> firstOperand - secondOperand
            "*" -> firstOperand * secondOperand
            "/" -> if (secondOperand != 0.0) firstOperand / secondOperand else Double.NaN
            "%" -> firstOperand % secondOperand
            else -> secondOperand
        }
        tvDisplay.text = if (result % 1 == 0.0) result.toInt().toString() else result.toString()
        isNewOp = true
        operator = null
    }
}