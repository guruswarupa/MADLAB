package com.guruswarupa.program2

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DisplayValidation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_validation)
        val display = findViewById<TextView>(R.id.textView2)

        val valid = intent.getStringExtra("Valid")
        display.text = valid
    }
}
