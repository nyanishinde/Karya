package com.example.karya

import android.media.Image
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment

class TaskPreviewDialogFragment:DialogFragment() {

    companion object{
        private const val ARG_TASK_NAME = "task_name"
        private const val ARG_TASK_IMAGE = "task_image"
        private const val ARG_TASK_PROGRESS = "task_progress"

        fun newInstance(taskName:String, taskImage:Int, taskProgress:Int):TaskPreviewDialogFragment{
            val fragment = TaskPreviewDialogFragment()
            val args = Bundle()
            args.putString(ARG_TASK_NAME,taskName)
            args.putInt(ARG_TASK_IMAGE,taskImage)
            args.putInt(ARG_TASK_PROGRESS,taskProgress)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.popup_task_preview,container,false)

        //Initializing all the views of popup activity here
        val goalName : TextView = view.findViewById(R.id.txtTaskName)
        val goalImage : ImageView = view.findViewById(R.id.imgTaskImage)
        val goalProgress : ProgressBar = view.findViewById(R.id.taskProgress)
        val updateValue : EditText = view.findViewById(R.id.edtValue)
        val btnPlus : ImageButton = view.findViewById(R.id.imgBtnPlus)
        val btnMinus : ImageButton = view.findViewById(R.id.imgBtnMinus)
        val btnCancel : TextView = view.findViewById(R.id.txtBtnCancel)
        val btnDone : TextView = view.findViewById(R.id.txtBtnDone)

        //Setting goal image here
        goalImage.setImageResource(R.drawable.img_meditation4x)

        //Retrieving data from arguments
        val taskName = arguments?.getString(ARG_TASK_NAME) ?:"Default task"
        val taskImage = arguments?.getInt(ARG_TASK_IMAGE) ?:R.drawable.img_running4x
        val taskProgress = arguments?.getInt(ARG_TASK_PROGRESS) ?:0

        //Setting retrieved data here
        goalName.text = taskName
        goalProgress.setProgress(taskProgress,true)
        goalImage.setImageResource(taskImage)

        //Apply the logic to add and subtract the value into the database
        val value = updateValue.text.toString()
        btnPlus.setOnClickListener {
            if (value.isNotEmpty()) {
                Toast.makeText(requireContext(), "$value added to target", Toast.LENGTH_SHORT)
                    .show()
            }else{
                Toast.makeText(requireContext(), "Please enter a value", Toast.LENGTH_SHORT).show()
            }
        }
        btnMinus.setOnClickListener {
            if (value.isNotEmpty()) {
                Toast.makeText(
                    requireContext(),
                    "$value subtracted from the target",
                    Toast.LENGTH_SHORT
                ).show()
            }else{
                Toast.makeText(requireContext(), "Please enter value", Toast.LENGTH_SHORT).show()
            }
        }
        btnCancel.setOnClickListener {
            dismiss()
        }

        btnDone.setOnClickListener {
            dismiss()
        }
        return view
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout((resources.displayMetrics.widthPixels*0.85).toInt(),ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
    }
}