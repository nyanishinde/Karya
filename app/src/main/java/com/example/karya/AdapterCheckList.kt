package com.example.karya

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class AdapterCheckList(
    private val onTaskChecked: (TasksDC,Boolean)->Unit,
    private val completedTasks: LiveData<List<Int>>):
    ListAdapter<TasksDC, AdapterCheckList.CheckListViewHolder>(TaskDiffCallBack()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterCheckList.CheckListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_dailytask,parent,false)
        return CheckListViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: AdapterCheckList.CheckListViewHolder,
        position: Int
    ) {
        val task=getItem(position)
        completedTasks.observeForever { completedIds->
            val isChecked=completedIds.contains(task.taskId)
            holder.bind(task,isChecked,onTaskChecked)
        }
    }

    class CheckListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val checkBox: CheckBox = itemView.findViewById(R.id.checkBoxItem)

        fun bind(task: TasksDC, isChecked: Boolean, onTaskChecked: (TasksDC, Boolean) -> Unit){
            checkBox.text=task.taskName
            checkBox.setOnCheckedChangeListener(null)
            checkBox.isChecked=isChecked

            checkBox.setOnCheckedChangeListener { _,isChecked ->
                onTaskChecked(task,isChecked)
            }
        }
    }

    class TaskDiffCallBack: DiffUtil.ItemCallback<TasksDC>(){
        override fun areContentsTheSame(oldItem: TasksDC, newItem: TasksDC): Boolean {
            return oldItem==newItem
        }

        override fun areItemsTheSame(oldItem: TasksDC, newItem: TasksDC): Boolean {
            return oldItem.taskId==newItem.taskId
        }
    }

}