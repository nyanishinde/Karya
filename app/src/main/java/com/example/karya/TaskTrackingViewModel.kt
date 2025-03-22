package com.example.karya

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.util.Calendar

class TaskTrackingViewModel(application: Application): AndroidViewModel(application) {
    private val taskRepository: TasksRepository
    private val taskTrackingRepository : TaskTrackingRepository
    val allTasks: LiveData<List<TasksDC>>
    val completedTaskIds: LiveData<List<Int>>
    init {
        val taskDao= DBApp.getDatabase(application).tasksDao()
        val trackingDao = DBApp.getDatabase(application).taskTrackingDoa()
        taskRepository= TasksRepository(taskDao)
        taskTrackingRepository= TaskTrackingRepository(trackingDao)
        allTasks=taskRepository.allTasks

        val date= String.format("%d-%d-%d",
            Calendar.getInstance().get(Calendar.DAY_OF_MONTH),
            Calendar.getInstance().get(Calendar.MONTH)+1,
            Calendar.getInstance().get(Calendar.YEAR))
        completedTaskIds=taskTrackingRepository.getCompletedTaskIds(date)
    }

    fun upsertTaskProgress(progress: TaskTrackingDC) = viewModelScope.launch {
        taskTrackingRepository.upsertTaskProgress(progress)
    }
    fun getCompletedDaysInMonth(taskId: Int,yearMonth: String) = viewModelScope.launch {
        taskTrackingRepository.getCompletedDaysInMonth(taskId,yearMonth)
    }
    fun getCountOfTaskCompletedOnDate(date: String) = viewModelScope.launch {
        taskTrackingRepository.getCountOfTaskCompletedOnDate(date)
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
        taskTrackingRepository.insertTaskTracking(taskTrackingEntry)
    }
}