package com.example.karya

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TasksViewModel(application: Application): AndroidViewModel(application) {
    private val repository: TasksRepository
    val allTasks : LiveData<List<TaskDC>>

    init {
        val taskDao = DBApp.getDatabase(application).tasksDao()
        repository = TasksRepository(taskDao)
        allTasks=repository.allTasks
    }

    fun upsertTask(task : TaskDC)=viewModelScope.launch {
        repository.upsertTask(task)
    }

    fun deleteTaskById(id: Int) = viewModelScope.launch {
        repository.deleteTaskById(id)
    }

    fun deleteAllTasks()=viewModelScope.launch {
        repository.deleteAllTasks()
    }

}