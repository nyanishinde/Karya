package com.example.karya

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_tasks")
data class TasksDC(
    @PrimaryKey(autoGenerate = true) val taskId : Int = 0,
    val taskName: String,
    val taskImageResId: Int
)
