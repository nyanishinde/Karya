package com.example.karya

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class AdapterUpcomingEvents(private val events : MutableList<DCEventDetails>):RecyclerView.Adapter<AdapterUpcomingEvents.EventsViewHolder>() {
    inner class EventsViewHolder(view : View) : RecyclerView.ViewHolder(view){

        val eventName : TextView = view.findViewById(R.id.tvEventTitle)
        val eventTime : TextView = view.findViewById(R.id.tvEventTime)
        val eventDate : TextView = view.findViewById(R.id.tvEventDate)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_upcomingsevents,parent,false)
        return EventsViewHolder(view)
    }

    override fun getItemCount(): Int = events.size

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        val event = events[position]

        holder.eventName.text = event.eventTitle
        holder.eventDate.text = event.eventDate
        holder.eventTime.text = event.eventTime
    }
}