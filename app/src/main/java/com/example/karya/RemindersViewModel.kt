package com.example.karya

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class RemindersViewModel(application: Application):AndroidViewModel(application) {
    private val repository: RemindersRepository
    val allReminders : LiveData<List<RemindersDC>>

    init {
        val remindersDoa = DBApp.getDatabase(application).reminderDao()
        repository=RemindersRepository(remindersDoa)
        allReminders = repository.allReminder
    }

    fun upsertReminder(reminder : RemindersDC) = viewModelScope.launch {
        repository.upsert(reminder)
    }

    fun updateReminder(reminder: RemindersDC) = viewModelScope.launch {
        repository.update(reminder)
    }

    fun deleteReminder(reminder: RemindersDC) = viewModelScope.launch {
        repository.delete(reminder)
    }

    fun deleteReminderById(id:Int) = viewModelScope.launch {
        repository.deleteById(id)
    }


}