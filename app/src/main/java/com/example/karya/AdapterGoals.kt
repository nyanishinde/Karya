package com.example.karya

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView

class AdapterGoals(private val context: Context,
                   private val goals: MutableList<DCGoals> ):RecyclerView.Adapter<AdapterGoals.GoalsViewHolder>() {
    inner class GoalsViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val goalImage : ImageView = view.findViewById(R.id.imgGoalImage)
        val goalName : TextView = view.findViewById(R.id.txtGoalName)
        val goalProgressBar : ProgressBar = view.findViewById(R.id.pbGoalProgress)
        val goalCard : MaterialCardView = view.findViewById(R.id.cardGoal)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoalsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_goals,parent,false)
        return GoalsViewHolder(view)
    }

    override fun getItemCount(): Int = goals.size

    override fun onBindViewHolder(holder: GoalsViewHolder, position: Int) {
        val item=goals[position]

        val strokeColor = ContextCompat.getColor(context,item.strokeColor)
        holder.goalCard.strokeColor = strokeColor

        holder.goalImage.setImageResource(item.goalImageResId)
        holder.goalName.text = item.goalName
        holder.goalProgressBar.setProgress(item.progress,true)

        //Handling click event on item click
        holder.itemView.setOnClickListener{
            //Calling the dialog here
            val goalDialog = TaskPreviewDialogFragment.newInstance(
                item.goalName,item.goalImageResId,item.progress
            )
            goalDialog.show((context as AppCompatActivity).supportFragmentManager,"TaskPreviewDialog")
        }

    }
}