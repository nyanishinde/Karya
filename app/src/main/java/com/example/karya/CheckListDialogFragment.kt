package com.example.karya

import android.app.AlertDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CheckListDialogFragment:DialogFragment() {

    private lateinit var taskDate:TextView
    private lateinit var taskCounter:TextView
    private lateinit var btnAddMoreTasks:TextView
    private lateinit var btnDone:TextView
    private lateinit var btnCancel:TextView
    private lateinit var taskRecyclerView: RecyclerView
    private lateinit var adapterCheckList: AdapterCheckList
    private val checkListItem = mutableListOf(
        DCCheckListItem("Study",false),
        DCCheckListItem("Meetings",false),
        DCCheckListItem("Evening walk",false),
        DCCheckListItem("Dinner prep",false),
        DCCheckListItem("Shower",false),
        DCCheckListItem("Meditation",false),
        DCCheckListItem("Workout",false)
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.popup_dailytasklist,container,false)

        //Declaring the views
        taskDate=view.findViewById(R.id.tvTodayDate)
        taskCounter=view.findViewById(R.id.tvTaskCounter)
        btnAddMoreTasks=view.findViewById(R.id.tvAddMoreTask)
        btnDone=view.findViewById(R.id.tvBtnDone)
        btnCancel=view.findViewById(R.id.tvBtnCancel)

        //Declaring recyclerview and adapter
        taskRecyclerView = view.findViewById(R.id.rvDailyTasks)
        taskRecyclerView.layoutManager=LinearLayoutManager(requireContext())
        //Creating and setting adapter for task list recyclerView
        adapterCheckList = AdapterCheckList(checkListItem){checked,total ->
            taskCounter.text="$checked/$total"
        }
        taskRecyclerView.adapter=adapterCheckList

        //Initialing a calender instance to get current date
        val calendar = Calendar.getInstance()
        taskDate.text=String.format("%d/%d/%d",calendar.get(Calendar.DAY_OF_MONTH),calendar.get(Calendar.MONTH)+1,calendar.get(Calendar.YEAR))

        //Setting the text of counter
        val checkListTotalItems=checkListItem.size
        val checkListCheckedItemsCount=checkListItem.count(){it.isChecked}
        taskCounter.text="$checkListCheckedItemsCount/$checkListTotalItems"

        //Setting click event on add more task
        btnAddMoreTasks.setOnClickListener {
            val addTaskDialog = AddNewTaskDialogFragment()
            addTaskDialog.show(parentFragmentManager,"Add new Task")
        }

        btnDone.setOnClickListener {
            dismiss()
        }

        btnCancel.setOnClickListener {
            dismiss()
        }

        return view
    }

    //Setting the size of dialog when it is opened
    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout((resources.displayMetrics.widthPixels * 0.85).toInt(),ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
    }
}