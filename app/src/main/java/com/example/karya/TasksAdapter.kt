package com.example.karya

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView

class TasksAdapter : ListAdapter<TasksDC, TasksAdapter.TasksViewHolder>(DIFF_CALLBACK) {

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TasksDC>(){
            override fun areItemsTheSame(oldItem: TasksDC, newItem: TasksDC): Boolean {
                return oldItem.taskId == newItem.taskId
            }

            override fun areContentsTheSame(oldItem: TasksDC, newItem: TasksDC): Boolean {
                return oldItem==newItem
            }
        }
    }

    inner class TasksViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val taskName: TextView = itemView.findViewById(R.id.txtTaskName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_taskname,parent,false)
        return TasksViewHolder(view)
    }

    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        val task=getItem(position)
        holder.taskName.text=task.taskName
    }

}