package com.example.karya

import androidx.lifecycle.LiveData

class TaskTrackingRepository(private val taskTrackingDao: TaskTrackingDao) {

    suspend fun upsertTaskProgress(progress: TaskTrackingDC){
        taskTrackingDao.upsertTaskProgress(progress)
    }
    fun getCompletedDaysInMonth(taskId: Int,yearMonth: String){
        taskTrackingDao.getCompletedDaysInMonth(taskId,yearMonth)
    }
    fun getCountOfTaskCompletedOnDate(date: String){
        taskTrackingDao.getCountOfTaskCompletedOnDate(date)
    }
    suspend fun insertTaskTracking(taskTracking: TaskTrackingDC){
        taskTrackingDao.insetTaskTracking(taskTracking)
    }
}