package com.example.karya

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class TaskTrackingViewModel(application: Application): AndroidViewModel(application) {
    private val taskRepository: TasksRepository
    private val repository : TaskTrackingRepository
    val allTasks: LiveData<List<TasksDC>>
    init {
        val taskDao= DBApp.getDatabase(application).tasksDao()
        val trackingDao = DBApp.getDatabase(application).taskTrackingDoa()
        taskRepository= TasksRepository(taskDao)
        repository= TaskTrackingRepository(trackingDao)
        allTasks=taskRepository.allTasks
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

    fun insertTaskTracking(taskId: Int) = viewModelScope.launch {
        val calender= Calendar.getInstance()
        val currentDate= String.format("%d-%d-%d",calender.get(Calendar.DAY_OF_MONTH),calender.get(
            Calendar.MONTH)+1,calender.get(Calendar.YEAR))

        val taskTrackingEntry= TaskTrackingDC(
            taskId=taskId,
            progressDate = currentDate,
            isCompleted = true
        )
        repository.insertTaskTracking(taskTrackingEntry)
    }
}