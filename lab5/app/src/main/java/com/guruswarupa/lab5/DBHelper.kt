package com.guruswarupa.lab5

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, "StudentDB", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE students(id INTEGER PRIMARY KEY, name TEXT, marks INTEGER)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS students")
        onCreate(db)
    }

    fun insertData(id: Int, name: String, marks: Int): Boolean {
        val db = writableDatabase
        val cv = ContentValues()
        cv.put("id", id)
        cv.put("name", name)
        cv.put("marks", marks)
        val result = db.insert("students", null, cv)
        return result != -1L
    }

    fun updateData(id: Int, name: String, marks: Int): Boolean {
        val db = writableDatabase
        val cv = ContentValues()
        cv.put("name", name)
        cv.put("marks", marks)
        val result = db.update("students", cv, "id=?", arrayOf(id.toString()))
        return result > 0
    }

    fun deleteData(id: Int): Boolean {
        val db = writableDatabase
        val result = db.delete("students", "id=?", arrayOf(id.toString()))
        return result > 0
    }

    fun getAllData(): String {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM students", null)
        var data = ""

        while (cursor.moveToNext()) {
            data += "ID: ${cursor.getInt(0)}\n"
            data += "Name: ${cursor.getString(1)}\n"
            data += "Marks: ${cursor.getInt(2)}\n\n"
        }
        cursor.close()
        return data
    }
}
