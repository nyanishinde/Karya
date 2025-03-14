package com.example.karya

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "task_tracking",
    foreignKeys = [ForeignKey(entity = TasksDC::class, parentColumns = ["taskId"], childColumns = ["taskId"], onDelete = CASCADE)])
data class TaskTrackingDC(
    @PrimaryKey(autoGenerate = true) val progressId : Int =0,
    val taskId: Int,
    val progressDate: String,
    val isCompleted : Boolean
)
