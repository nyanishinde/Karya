package com.example.karya

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import java.util.Calendar

class ReminderDialogFragment:DialogFragment() {

    private lateinit var remindersViewModel: RemindersViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.popup_add_reminder,container,false)

        //Initializing all views
        val reminderTitle = view.findViewById<EditText>(R.id.edtReminderTitle)
        val reminderDate = view.findViewById<EditText>(R.id.edtReminderDate)
        val reminderTime = view.findViewById<EditText>(R.id.edtReminderTime)
        val imgBtnTime = view.findViewById<ImageButton>(R.id.imgBtnTime)
        val imgBtnCalender = view.findViewById<ImageButton>(R.id.imgBtnCalender)
        val btnDone = view.findViewById<TextView>(R.id.btnDone)
        val btnCancel = view.findViewById<TextView>(R.id.btnCancel)

        //Setting default date and time
        val calender = Calendar.getInstance()
        val defaultDate = String.format("%d/%d/%d",calender.get(Calendar.DAY_OF_MONTH),calender.get(Calendar.MONTH)+1,calender.get(Calendar.YEAR))
        reminderDate.setText(defaultDate)
        val defaultTime = String.format("%02d:%02d",calender.get(Calendar.HOUR_OF_DAY),calender.get(Calendar.MINUTE))
        reminderTime.setText(defaultTime)
        
        //Setting date picker dialog on press of calender image button
        imgBtnCalender.setOnClickListener { 
            val datePickerDialog = DatePickerDialog(
                requireContext(),
                {_,year,month,dayOfMonth ->
                    val selectedDate = "$dayOfMonth-${month+1}-$year"
                    reminderDate.setText(selectedDate)
                },
                calender.get(Calendar.YEAR),
                calender.get(Calendar.MONTH),
                calender.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.show()
        }
        
        //Setting time picker dialog on press of clock image button
        imgBtnTime.setOnClickListener { 
            val timePickerDialog = TimePickerDialog(
                requireContext(),
                {_,hourOfDay,minute->
                    val selectedTime = String.format("%02d:%02d",hourOfDay, minute)
                    reminderTime.setText(selectedTime)
                },
                calender.get(Calendar.HOUR_OF_DAY),
                calender.get(Calendar.MINUTE),
                true
            )
            timePickerDialog.show()
        }

        //Setting Done and Cancel Button
        btnDone.setOnClickListener {
            //fetching all the values
            val name=reminderTitle.text.toString().trim()
            val date = reminderDate.text.toString().trim()
            val time = reminderTime.text.toString().trim()

            //Initializing view model ob to add values in the database
            remindersViewModel = ViewModelProvider(requireActivity())[RemindersViewModel::class.java]

            if(name.isNotEmpty() && date.isNotEmpty() && time.isNotEmpty()){
                val reminder = RemindersDC(reminderName = name, reminderDate = date, reminderTime = time)

                remindersViewModel.upsertReminder(reminder)
                Toast.makeText(requireContext(), "Reminder added", Toast.LENGTH_SHORT).show()
                dismiss()
            }else{
                Toast.makeText(requireContext(), "Please enter all values", Toast.LENGTH_SHORT).show()
            }
        }

        btnCancel.setOnClickListener {
            dismiss()
        }

        return view
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout((resources.displayMetrics.widthPixels * 0.85).toInt(),ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
    }

}