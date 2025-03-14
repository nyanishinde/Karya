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

    //get monthly progress of each task
    @Query("SELECT COUNT(DISTINCT progressDate) FROM task_tracking WHERE taskId=:taskId AND isCompleted=1 AND strftime('%Y-%m', progressDate) = :yearMonth")
    fun getCompletedDaysInMonth(taskId: Int,yearMonth: String): LiveData<Int>

    //get weekly daily progress of all task for graph
    @Query("SELECT COUNT(*) FROM task_tracking WHERE progressDate=:date")
    fun getCountOfTaskCompletedOnDate(date: String): LiveData<Int>
}