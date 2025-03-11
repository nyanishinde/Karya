package com.example.karya

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_goals")
data class GoalsDC(
    @PrimaryKey (autoGenerate = true) val goalId: Int = 0,
    val goalName: String,
    val goalTarget: Int,
    val goalPriority: Int = 1,
    val goalImage: Int
)
