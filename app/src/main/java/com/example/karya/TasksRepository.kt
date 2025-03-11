package com.example.karya

import androidx.lifecycle.LiveData

class TasksRepository(private val tasksDao: TasksDao) {
    val allTasks : LiveData<List<TaskDC>> = tasksDao.getAllTasks()

    suspend fun upsertTask(task: TaskDC){
        tasksDao.upsertTask(task)
    }

    fun deleteTaskById(id: Int){
        tasksDao.deleteTaskById(id)
    }

    fun deleteAllTasks(){
        tasksDao.deleteAllTasks()
    }

}