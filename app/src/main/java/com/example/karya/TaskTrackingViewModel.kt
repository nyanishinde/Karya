package com.example.karya

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TaskTrackingViewModel(application: Application): AndroidViewModel(application) {
    private val repository : TaskTrackingRepository
    init {
        val trackingDao = DBApp.getDatabase(application).taskTrackingDoa()
        repository= TaskTrackingRepository(trackingDao)
    }

    fun upsertTaskProgress(progress: TaskTrackingDC) = viewModelScope.launch {
        repository.upsertTaskProgress(progress)
    }
    fun getCompletedDaysInMonth(taskId: Int,yearMonth: String) = viewModelScope.launch {
        repository.getCompletedDaysInMonth(taskId,yearMonth)
    }
    fun getCountOfTaskCompletedOnDate(date: String) = viewModelScope.launch {
        repository.getCountOfTaskCompletedOnDate(date)
    }
}