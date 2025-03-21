package com.example.karya

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class GoalsViewModel(application: Application): AndroidViewModel(application) {
    private val repository: GoalsRepository
    private val progressRepository : GoalTrackingRepository
    val allGoals: LiveData<List<GoalsDC>>

    //Combined list for goals and progress
    val goalsWithProgress: MediatorLiveData<List<GoalWithProgress>> = MediatorLiveData()

    init {
        val goalDao = DBApp.getDatabase(application).goalsDao()
        repository = GoalsRepository(goalDao)
        allGoals = repository.allGoals

        val progressDao= DBApp.getDatabase(application).goalTrackingDao()
        progressRepository= GoalTrackingRepository(progressDao)

        goalsWithProgress.addSource(allGoals) {goalsList->
            mergeData(goalsList)
        }
    }

    private fun mergeData(goalsList: List<GoalsDC>){
        if (goalsList==null) return

        val updatedList = mutableListOf<GoalWithProgress>()
        for (goal in goalsList){
            viewModelScope.launch {
                val progress = progressRepository.getTotalProgress(goal.goalId).value?:0
                updatedList.add(
                    GoalWithProgress(
                        goal.goalId,
                        goal.goalName,
                        goal.goalImage,
                        goal.goalTarget,
                        progress
                    )
                )
                goalsWithProgress.postValue(updatedList)
            }
        }

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