package com.example.karya

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import java.time.YearMonth

@Dao
interface TaskTrackingDao {

    @Upsert
    suspend fun upsertTaskProgress(progress: TaskTrackingDC)

    @Upsert
    suspend fun insetTaskTracking(task: TaskTrackingDC)

    //get monthly progress of each task
    @Query("SELECT COUNT(DISTINCT progressDate) FROM task_tracking WHERE taskId=:taskId AND isCompleted=1 AND strftime('%Y-%m', progressDate) = :yearMonth")
    fun getCompletedDaysInMonth(taskId: Int,yearMonth: String): LiveData<Int>

    //get weekly daily progress of all task for graph
    @Query("SELECT COUNT(*) FROM task_tracking WHERE progressDate=:date")
    fun getCountOfTaskCompletedOnDate(date: String): LiveData<Int>

    //get count of completed task for today
    @Query("SELECT COUNT(*) FROM task_tracking WHERE progressDate=:date AND isCompleted=1")
    fun getCompletedTasksCount(date: String): LiveData<Int>

    @Query("SELECT taskId FROM task_tracking WHERE progressDate=:date AND isCompleted=1")
    fun getCompletedTaskIds(date: String): LiveData<List<Int>>
}