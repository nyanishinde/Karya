package com.example.karya

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface TasksDao {

    @Upsert
    suspend fun upsertTask(task: TasksDC)

    @Query("DELETE FROM user_tasks WHERE taskId=:id")
    suspend fun deleteTaskById(id: Int)

    @Query("SELECT * FROM user_tasks ORDER BY taskName ASC")
    fun getAllTasks(): LiveData<List<TasksDC>>

    @Query("DELETE FROM user_tasks")
    suspend fun deleteAllTasks()

    @Query("SELECT COUNT(*) FROM user_tasks")
    fun getTotalCountOfTasks(): LiveData<Int>
}