package com.example.karya

import android.widget.ImageView
import android.widget.TextView

data class DCDailyTaskProgressOverview(
    val taskImageResId:Int,
    val taskName:String,
    val taskPerformDays:Int,
    val taskTotalDays:Int
)
