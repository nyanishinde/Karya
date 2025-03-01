package com.example.karya

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView

//creating an adapter that takes a item list and a lamda function for showing text counter
//and it returns a view holder
class AdapterCheckList(
    private val items:MutableList<DCCheckListItem>,
    private val updateCounter:(Int,Int) -> Unit
):RecyclerView.Adapter<AdapterCheckList.CheckListViewHolder>() {

    //Initializing handler to add a delay while adding new item in task list
    private val handler = Handler(Looper.getMainLooper())

    //Creating inner class which will pass the view holder
    inner class CheckListViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val checkBox:CheckBox = itemView.findViewById(R.id.checkBoxItem)
    }

    //Initializing the view for recycler view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterCheckList.CheckListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_dailytask,parent,false)
        return CheckListViewHolder(view)
    }

    //Setting different values to their attributes
    override fun onBindViewHolder(holder: AdapterCheckList.CheckListViewHolder, position: Int) {
        val item = items[position]

        holder.checkBox.setOnCheckedChangeListener(null)

        holder.checkBox.text = item.taskName
        holder.checkBox.isChecked = item.isChecked

        holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
            item.isChecked=isChecked
            handler.postDelayed({
                reOrderList()
            },100)
        }
    }

    //Declaring size of recycler view item list
    override fun getItemCount(): Int = items.size

    // sorting the list based on checked and unchecked
    private fun reOrderList() {
        items.sortBy { it.isChecked }
        notifyDataSetChanged()
        updateCounter(items.count { it.isChecked },items.size)
    }

    //Adding new task to list
    fun addTask(taskName:String){
        items.add(DCCheckListItem(taskName,false))
        reOrderList()
    }
}