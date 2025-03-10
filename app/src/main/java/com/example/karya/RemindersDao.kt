package com.example.karya

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert

@Dao
interface RemindersDao {
    @Upsert
    suspend fun upsertReminder(reminder:RemindersDC)

    @Delete
    suspend fun deleteReminder(reminder:RemindersDC)

    @Update
    suspend fun updateReminder(reminder: RemindersDC)

    //Will delete a reminder based on id
    @Query("DELETE FROM reminders WHERE reminderID= :id")
    suspend fun deleteReminderById(id :Int)

    //Will fetch all reminders from table
    @Query("SELECT * FROM reminders")
    fun getAllReminders():LiveData<List<RemindersDC>>

    //Will fetch all reminders from tables but in sorted order
    @Query("SELECT * FROM reminders ORDER BY reminderDate,reminderTime")
    fun getAllRemindersByOrder():LiveData<List<RemindersDC>>
}