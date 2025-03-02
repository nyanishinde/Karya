package com.example.karya

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.progressindicator.LinearProgressIndicator

class AdapterTaskMonthlyOverview(private val taskItemList: MutableList<DCDailyTaskProgressOverview>):RecyclerView.Adapter<AdapterTaskMonthlyOverview.OverViewViewHolder>() {
    inner class OverViewViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val taskImage : ImageView = itemView.findViewById(R.id.imgTaskImage)
        val taskName : TextView = itemView.findViewById(R.id.txtTaskName)
        val progressIndicator : LinearProgressIndicator = itemView.findViewById(R.id.taskProgressIndicator)
        val taskProgress : TextView = itemView.findViewById(R.id.txtTaskProgressDays)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OverViewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_monthly_overview,parent,false)
        return OverViewViewHolder(view)
    }

    override fun getItemCount(): Int = taskItemList.size

    override fun onBindViewHolder(holder: OverViewViewHolder, position: Int) {
        val item = taskItemList[position]

        val totalDays:Int = item.taskTotalDays
        val performedDays:Int = item.taskPerformDays
        val progress:Int = if(totalDays>0) {
            ((performedDays.toFloat() / totalDays) * 100).toInt()
        } else 0

        holder.taskImage.setImageResource(item.taskImageResId)
        holder.taskName.text=item.taskName
        holder.taskProgress.text="$performedDays/$totalDays"
        holder.progressIndicator.progress=progress
    }
}