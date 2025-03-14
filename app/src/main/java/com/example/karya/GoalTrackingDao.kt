package com.example.karya

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface GoalTrackingDao {

    @Upsert
    suspend fun upsertProgress(progress: GoalTrackingDC)

    @Query("SELECT SUM(progressChange) FROM goal_tracking WHERE goalId-:goalId")
    fun getTotalProgress(goalId:Int): LiveData<Int>
}