package com.example.karya

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class GoalsViewModel(application: Application): AndroidViewModel(application) {
    private val repository: GoalsRepository
    val allGoals: LiveData<List<GoalsDC>>

    init {
        val goalDao = DBApp.getDatabase(application).goalsDao()
        repository = GoalsRepository(goalDao)
        allGoals = repository.allGoals
    }

    fun upsertGoals(goal: GoalsDC)=viewModelScope.launch {
        repository.upsert(goal)
    }

    fun deleteGoalById(id: Int)=viewModelScope.launch {
        repository.deleteGoalById(id)
    }

    fun getAllGoalsByPriorityHighToLow() =viewModelScope.launch {
        repository.getAllGoalsByPriorityHighToLow()
    }

    fun getAllGoalsByPriorityLowToHigh() = viewModelScope.launch {
        repository.getAllGoalsByPriorityLowToHigh()
    }

    fun updateGoalTargetById(newTarget: Int,id: Int) = viewModelScope.launch {
        repository.updateGoalTargetById(newTarget,id)
    }
}