package com.example.karya

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reminders")
data class RemindersDC(
    @PrimaryKey(autoGenerate = true) val reminderID : Int = 0,
    val reminderName : String,
    val reminderDate : String,
    val reminderTime : String,
    val reminderCompleted : Boolean = false
)
