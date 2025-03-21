package com.example.karya

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class WeeklyGoalsAdapter: ListAdapter<GoalWithProgress, WeeklyGoalsAdapter.GoalsViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GoalsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_goals,parent,false)
        return GoalsViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: GoalsViewHolder,
        position: Int
    ) {
        val goal=getItem(position)
        holder.goalName.text=goal.goalName.toString()
        holder.goalImage.setImageResource(goal.goalImage)
        val progress = goal.totalProgress/goal.goalTarget
        holder.goalProgress.setProgress(progress,true)
    }

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<GoalWithProgress>(){
            override fun areContentsTheSame(
                oldItem: GoalWithProgress,
                newItem: GoalWithProgress
            ): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(
                oldItem: GoalWithProgress,
                newItem: GoalWithProgress
            ): Boolean {
                return oldItem.goalId == newItem.goalId
            }
        }
    }
    inner class GoalsViewHolder(view: View): RecyclerView.ViewHolder(view){
        val goalImage : ImageView = view.findViewById(R.id.imgGoalImage)
        val goalName : TextView = view.findViewById(R.id.txtGoalName)
        val goalProgress: ProgressBar = view.findViewById(R.id.pbGoalProgress)
    }
}