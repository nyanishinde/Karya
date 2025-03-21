package com.example.karya

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider

class AddNewTaskDialogFragment: DialogFragment() {

    private lateinit var tasksViewModel: TasksViewModel
    private lateinit var btnDone: TextView
    private lateinit var btnCancel: TextView
    private lateinit var taskImage: ImageView
    private lateinit var imgAddBtn: ImageView
    private lateinit var taskName: EditText
    private var selectedImageRes : Int= R.drawable.img_meditation
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.popup_add_new_task,container,false)

        btnDone = view.findViewById(R.id.txtBtnDone)
        btnCancel = view.findViewById(R.id.txtBtnCancel)
        taskImage = view.findViewById(R.id.taskImagePreview)
        imgAddBtn = view.findViewById(R.id.btnAddImage)
        taskName = view.findViewById(R.id.edtTaskName)

        imgAddBtn.setOnClickListener {
            val bottomSheet = ImagePickerBottomSheetDialog { selectedImage ->
                selectedImageRes = selectedImage
                taskImage.setImageResource(selectedImage)
                taskImage.visibility = View.VISIBLE // Show selected image
                imgAddBtn.visibility = View.GONE // Hide plus icon
            }
            bottomSheet.show(childFragmentManager, "ImagePickerBottomSheet")
        }

        btnDone.setOnClickListener {

            val newTaskImageResId:Int = selectedImageRes
            val newTaskName: String = taskName.text.toString()
            //Initializing taskViewmodel to add values in the database
            tasksViewModel= ViewModelProvider(requireActivity())[TasksViewModel::class.java]

            if(newTaskName.isNotEmpty()){
                val newTask= TasksDC(taskName=newTaskName, taskImageResId = newTaskImageResId)
                tasksViewModel.upsertTask(newTask)
                Toast.makeText(requireContext(), "New task added", Toast.LENGTH_SHORT).show()
                dismiss()
            }else{
                Toast.makeText(requireContext(), "Fields cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }

        btnCancel.setOnClickListener {
            dismiss()
        }


        return view;
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout((resources.displayMetrics.widthPixels*0.85).toInt(),ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
    }

}