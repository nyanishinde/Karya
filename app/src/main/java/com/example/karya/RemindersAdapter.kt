package com.example.karya

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class RemindersAdapter:ListAdapter<RemindersDC,RemindersAdapter.ReminderViewHolder>(DIFF_CALLBACK) {

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<RemindersDC>(){
            override fun areItemsTheSame(oldItem: RemindersDC, newItem: RemindersDC): Boolean {
                return oldItem.reminderID == newItem.reminderID
            }

            override fun areContentsTheSame(oldItem: RemindersDC, newItem: RemindersDC): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class ReminderViewHolder(itemView : View):RecyclerView.ViewHolder(itemView){
        val eventDate : TextView = itemView.findViewById(R.id.tvEventDate)
        val eventTitle : TextView = itemView.findViewById(R.id.tvEventTitle)
        val eventTime : TextView = itemView.findViewById(R.id.tvEventTime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReminderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_upcomingsevents,parent,false)
        return ReminderViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReminderViewHolder, position: Int) {
        val reminder = getItem(position)
        holder.eventDate.text = reminder.reminderDate.substring(0,2)
        holder.eventTitle.text = reminder.reminderName
        holder.eventTime.text = reminder.reminderTime
    }
}