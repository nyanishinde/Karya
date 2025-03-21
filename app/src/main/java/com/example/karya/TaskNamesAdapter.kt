package com.example.karya

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class TaskNamesAdapter:ListAdapter<TasksDC, TaskNamesAdapter.TaskViewHolder>(DIFF_CALLBACK) {

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TasksDC>(){
            override fun areContentsTheSame(oldItem: TasksDC, newItem: TasksDC): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: TasksDC, newItem: TasksDC): Boolean {
                return oldItem.taskId == newItem.taskId
            }
        }
    }
    inner class TaskViewHolder(view: View): RecyclerView.ViewHolder(view){
        val taskName: TextView = view.findViewById(R.id.txtTaskName)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_taskname,parent,false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: TaskViewHolder,
        position: Int
    ) {
        val tasks=getItem(position)
        holder.taskName.text=tasks.taskName
    }

}