package com.example.karya

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class GoalsAdapter: ListAdapter<GoalsDC, GoalsAdapter.GoalsViewHolder>(DIFF_CALLBACK) {

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<GoalsDC>(){
            override fun areItemsTheSame(oldItem: GoalsDC, newItem: GoalsDC): Boolean {
                return oldItem.goalId==newItem.goalId
            }

            override fun areContentsTheSame(oldItem: GoalsDC, newItem: GoalsDC): Boolean {
                return oldItem==newItem
            }
        }
    }

    inner class GoalsViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
        val goalName: TextView = itemView.findViewById(R.id.txtGoalName)
        val goalImage: ImageView = itemView.findViewById(R.id.imgGoalImage)
        val goalProgress : ProgressBar = itemView.findViewById(R.id.pbGoalProgress)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoalsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_goals,parent,false)
        return GoalsViewHolder(view)
    }

    override fun onBindViewHolder(holder: GoalsViewHolder, position: Int) {
        val goal = getItem(position)
        holder.goalName.text = goal.goalName
        holder.goalImage.setImageResource(goal.goalImage)
        //TODO: calculate progress of goal here and then set it
    }

}