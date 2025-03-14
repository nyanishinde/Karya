package com.example.karya

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class GoalTrackingViewModel(application: Application): AndroidViewModel(application) {

    private val repository: GoalTrackingRepository

    init {
        val trackingDao = DBApp.getDatabase(application).goalTrackingDao()
        repository= GoalTrackingRepository(trackingDao)
    }
    fun upsertProgress(progress: GoalTrackingDC) = viewModelScope.launch {
        repository.upsertProgress(progress)
    }

    fun getTotalProgress(goalId: Int) = viewModelScope.launch {
        repository.getTotalProgress(goalId)
    }

}