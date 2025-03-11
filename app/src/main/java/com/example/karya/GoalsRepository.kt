package com.example.karya

import androidx.lifecycle.LiveData

class GoalsRepository(private val goalsDao: GoalsDao) {
    val allGoals : LiveData<List<GoalsDC>> = goalsDao.getAllGoals()

    suspend fun upsert(goal: GoalsDC){
        goalsDao.upsertGoals(goal)
    }

    suspend fun deleteGoal(goal: GoalsDC){
        goalsDao.deleteGoal(goal)
    }

    suspend fun updateGoal(goal: GoalsDC){
        goalsDao.updateGoal(goal)
    }

    fun deleteGoalById(id: Int){
        goalsDao.deleteGoalById(id)
    }

    fun getAllGoalsByPriorityHighToLow(){
        goalsDao.getAllGoalsByPriorityHighToLow()
    }

    fun getAllGoalsByPriorityLowToHigh(){
        goalsDao.getAllGoalsByPriorityLowToHigh()
    }

    fun updateGoalTargetById(newTarget:Int,id: Int){
        goalsDao.updateGoalTargetById(newTarget,id)
    }

}