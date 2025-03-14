package com.example.karya

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "goal_tracking",
    foreignKeys = [ForeignKey(entity = GoalsDC::class, parentColumns = ["goalId"], childColumns = ["goalId"], onDelete = CASCADE)]
)
data class GoalTrackingDC(
    @PrimaryKey(autoGenerate = true) val progressId:Int = 0,
    val goalId: Int,
    val progressDate: String,
    val progressChange : Int
)
