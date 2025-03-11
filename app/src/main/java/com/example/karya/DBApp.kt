package com.example.karya

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RemindersDC::class, GoalsDC::class, TaskDC::class], version = 1)
abstract class DBApp:RoomDatabase() {
    abstract fun reminderDao():RemindersDao
    abstract fun goalsDao(): GoalsDao
    abstract fun tasksDao(): TasksDao

    companion object{
        @Volatile
        private var INSTANCE : DBApp ?= null

        fun getDatabase(context: Context):DBApp{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DBApp::class.java,
                    "app_database"
                ).build()
                INSTANCE=instance
                instance
            }
        }
    }
}