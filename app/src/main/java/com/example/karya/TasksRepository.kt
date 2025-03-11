package com.example.karya

import androidx.lifecycle.LiveData

class TasksRepository(private val tasksDao: TasksDao) {
    val allTasks : LiveData<List<TasksDC>> = tasksDao.getAllTasks()

    suspend fun upsertTask(task: TasksDC){
        tasksDao.upsertTask(task)
    }

    suspend fun deleteTaskById(id: Int){
        tasksDao.deleteTaskById(id)
    }

    suspend fun deleteAllTasks(){
        tasksDao.deleteAllTasks()
    }

}