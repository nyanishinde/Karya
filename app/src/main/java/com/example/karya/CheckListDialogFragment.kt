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

        //Initialing a calender instance to get current date
        val calendar = Calendar.getInstance()
        taskDate.text=String.format("%d/%d/%d",calendar.get(Calendar.DAY_OF_MONTH),calendar.get(Calendar.MONTH)+1,calendar.get(Calendar.YEAR))

        //Setting the text of counter
        val checkListTotalItems=checkListItem.size
        val checkListCheckedItemsCount=checkListItem.count(){it.isChecked}
        taskCounter.text="$checkListCheckedItemsCount/$checkListTotalItems"

        //Creating and setting adapter for task list recyclerView
        adapterCheckList = AdapterCheckList(checkListItem){checked,total ->
            taskCounter.text="$checked/$total"
        }
        taskRecyclerView.adapter=adapterCheckList

        //Setting click event on add more task
        btnAddMoreTasks.setOnClickListener {
            openAddMoreTaskDialog()
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

    //Function to show custom alert dialog to add new task
    private fun openAddMoreTaskDialog(){
        val edtTaskName = EditText(requireContext())
        edtTaskName.setHint("Enter task name")
        edtTaskName.setTextColor(resources.getColor(R.color.text_light))
        edtTaskName.setHintTextColor(resources.getColor(R.color.text_hint))
        edtTaskName.setBackgroundColor(resources.getColor(R.color.background_light))
        edtTaskName.setPadding(40,20,40,20)

        //creating dialog
        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("Add new task") //Adding title to the dialog
            .setView(edtTaskName)
            .setPositiveButton("Add"){dialog,_->  //Adding positive button
                val taskName=edtTaskName.text.toString().trim()
                if (taskName.isNotEmpty()){
                    adapterCheckList.addTask(taskName)
                }
                dialog.dismiss()
            }
            .setNegativeButton("Cancel"){dialog,_->   //Adding negative button
                dialog.dismiss()
            }.create()

        //changing the colors of add and cancel button
        dialog.setOnShowListener {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(resources.getColor(R.color.text_light))
            dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(resources.getColor(R.color.text_hint))
        }

        dialog.show()
    }

}