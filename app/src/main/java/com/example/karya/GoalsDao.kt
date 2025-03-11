package com.example.karya

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert

@Dao
interface GoalsDao {

    @Upsert
    suspend fun upsertGoals(goal: GoalsDC)

    @Delete
    suspend fun deleteGoal(goal: GoalsDC)

    @Update
    suspend fun updateGoal(goal: GoalsDC)

    //delete goal by its id
    @Query("DELETE FROM user_goals where goalId=:id")
    fun deleteGoalById(id: Int)

    //get all goals
    @Query("SELECT * FROM user_goals")
    fun getAllGoals(): LiveData<List<GoalsDC>>

    //get goals by priority high
    @Query("SELECT * FROM user_goals ORDER BY goalPriority DESC")
    fun getAllGoalsByPriorityHighToLow(): LiveData<List<GoalsDC>>

    //get goals by priority low
    @Query("SELECT * FROM user_goals ORDER BY goalPriority ASC")
    fun getAllGoalsByPriorityLowToHigh() : LiveData<List<GoalsDC>>

    @Query("UPDATE user_goals SET goalTarget = :newTarget where goalId = :id")
    fun updateGoalTargetById(id: Int,newTarget: Int)


}