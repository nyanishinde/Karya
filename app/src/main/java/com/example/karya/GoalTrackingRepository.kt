package com.example.karya

import androidx.lifecycle.LiveData

class GoalTrackingRepository(private val trackingDoa: GoalTrackingDao) {

    suspend fun upsertProgress(progress: GoalTrackingDC){
        trackingDoa.upsertProgress(progress)
    }
    fun getTotalProgress(goalId: Int): LiveData<Int>{
        return trackingDoa.getTotalProgress(goalId)
    }
}