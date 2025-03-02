package com.example.karya

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterTaskNames(private val names:MutableList<String>):RecyclerView.Adapter<AdapterTaskNames.TaskNamesViewHolder>() {
    class TaskNamesViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val taskName: TextView = itemView.findViewById(R.id.txtTaskName)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterTaskNames.TaskNamesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_taskname,parent,false)
        return TaskNamesViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdapterTaskNames.TaskNamesViewHolder, position: Int) {
        val itemName = names[position]
        holder.taskName.text=itemName
    }

    override fun getItemCount(): Int =names.size

}