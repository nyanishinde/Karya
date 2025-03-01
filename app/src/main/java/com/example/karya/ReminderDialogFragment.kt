package com.example.karya

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import java.util.Calendar
import kotlin.math.min

class ReminderDialogFragment:DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.popup_add_reminder,container,false)

        //Initializing all views
        val reminderTitle = view.findViewById<EditText>(R.id.edtReminderTitle)
        val reminderDescription = view.findViewById<EditText>(R.id.edtReminderDescription)
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
                    val selectedDate = "$dayOfMonth/${month+1}/$year"
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
            dismiss()
        }

        btnCancel.setOnClickListener {
            dismiss()
        }

        //Extracting all items of reminder
        val title = reminderTitle.text
        val description = reminderDescription.text
        val time = reminderTime.text
        val date = reminderDate.text

        return view
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout((resources.displayMetrics.widthPixels * 0.85).toInt(),ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
    }

}