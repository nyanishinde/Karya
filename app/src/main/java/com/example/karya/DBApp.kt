package com.example.karya

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RemindersDC::class, GoalsDC::class, TasksDC::class, GoalTrackingDC::class], version = 1, exportSchema = false)
abstract class DBApp:RoomDatabase() {
    abstract fun reminderDao():RemindersDao
    abstract fun goalsDao(): GoalsDao
    abstract fun tasksDao(): TasksDao
    abstract fun goalTrackingDao(): GoalTrackingDao
    abstract fun taskTrackingDao(): TaskTrackingDao

    companion object{
        @Volatile
        private var INSTANCE : DBApp ?= null

        fun getDatabase(context: Context):DBApp{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DBApp::class.java,
                    "app_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE=instance
                instance
            }
        }
    }
}