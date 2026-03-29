package com.guruswarupa.program2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val Uname = findViewById<EditText>(R.id.editTextText)
        val Pword = findViewById<EditText>(R.id.editTextText2)
        val btn = findViewById<Button>(R.id.button)

        btn.setOnClickListener {
            val intent = Intent(this, DisplayValidation::class.java)
            var UName = Uname.text.toString()
            var PWord = Pword.text.toString()
            if (isValidPassword( PWord)) {
                intent.putExtra("Valid", "Password is Valid")
            }else{
                intent.putExtra("Valid", "Password is not  Valid")
            }
            startActivity(intent)

        }

    }

    fun isValidPassword(password: String): Boolean {
        val passwordRegex = Regex("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")
        return passwordRegex.matches(password)
    }
}
