package com.guruswarupa.program3

import android.app.*
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TimePicker
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.setAlarm)

        button.setOnClickListener {

            val timePicker = TimePickerDialog(this,
                { _, hour, minute ->

                    val calendar = Calendar.getInstance()
                    calendar.set(Calendar.HOUR_OF_DAY, hour)
                    calendar.set(Calendar.MINUTE, minute)

                    val intent = Intent(this, AlarmReceiver::class.java)
                    val pendingIntent = PendingIntent.getBroadcast(
                        this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

                    val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager

                    alarmManager.set(
                        AlarmManager.RTC_WAKEUP,
                        calendar.timeInMillis,
                        pendingIntent
                    )

                }, 12, 0, true)

            timePicker.show()
        }
    }}
