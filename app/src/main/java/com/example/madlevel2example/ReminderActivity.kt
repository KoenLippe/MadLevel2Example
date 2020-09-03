package com.example.madlevel2example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ReminderActivity : AppCompatActivity() {

    data class Reminder(
        var reminderText: String
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reminder)
    }
}