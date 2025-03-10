package com.example.karya

import androidx.lifecycle.LiveData

class RemindersRepository(private val remindersDao: RemindersDao) {

    val allReminder : LiveData<List<RemindersDC>> = remindersDao.getAllRemindersByOrder()

    suspend fun upsert(reminders:RemindersDC){
        remindersDao.upsertReminder(reminders)
    }

    suspend fun update(reminders: RemindersDC){
        remindersDao.updateReminder(reminders)
    }

    suspend fun delete(reminders: RemindersDC){
        remindersDao.deleteReminder(reminders)
    }

    suspend fun deleteById(id:Int){
        remindersDao.deleteReminderById(id)
    }
}