package com.example.karya

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface TasksDao {

    @Upsert
    suspend fun addTask(task: TaskDC)

    @Query("DELETE FROM user_tasks WHERE taskId=:id")
    fun deleteTaskById(id: Int)

    @Query("SELECT * FROM user_tasks ORDER BY taskName ASC")
    fun getAllTasks(): LiveData<List<TaskDC>>
}