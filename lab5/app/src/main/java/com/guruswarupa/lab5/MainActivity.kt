package com.guruswarupa.lab5

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var db: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = DBHelper(this)

        val etId = findViewById<EditText>(R.id.etId)
        val etName = findViewById<EditText>(R.id.etName)
        val etMarks = findViewById<EditText>(R.id.etMarks)

        val btnInsert = findViewById<Button>(R.id.btnInsert)
        val btnUpdate = findViewById<Button>(R.id.btnUpdate)
        val btnDelete = findViewById<Button>(R.id.btnDelete)
        val btnView = findViewById<Button>(R.id.btnView)

        btnInsert.setOnClickListener {
            val idStr = etId.text.toString()
            val name = etName.text.toString()
            val marksStr = etMarks.text.toString()

            if (idStr.isEmpty() || name.isEmpty() || marksStr.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val success = db.insertData(
                idStr.toInt(),
                name,
                marksStr.toInt()
            )
            Toast.makeText(this, if (success) "Inserted" else "Failed", Toast.LENGTH_SHORT).show()
        }

        btnUpdate.setOnClickListener {
            val idStr = etId.text.toString()
            val name = etName.text.toString()
            val marksStr = etMarks.text.toString()

            if (idStr.isEmpty() || name.isEmpty() || marksStr.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val success = db.updateData(
                idStr.toInt(),
                name,
                marksStr.toInt()
            )
            Toast.makeText(this, if (success) "Updated" else "Failed", Toast.LENGTH_SHORT).show()
        }

        btnDelete.setOnClickListener {
            val idStr = etId.text.toString()
            if (idStr.isEmpty()) {
                Toast.makeText(this, "Please enter ID", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val success = db.deleteData(idStr.toInt())
            Toast.makeText(this, if (success) "Deleted" else "Failed", Toast.LENGTH_SHORT).show()
        }

        btnView.setOnClickListener {
            val data = db.getAllData()
            AlertDialog.Builder(this)
                .setTitle("Student Records")
                .setMessage(data)
                .setPositiveButton("OK", null)
                .show()
        }
    }
}
