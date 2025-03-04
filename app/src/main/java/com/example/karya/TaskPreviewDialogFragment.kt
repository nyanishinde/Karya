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
import androidx.fragment.app.DialogFragment
import org.w3c.dom.Text

class TaskPreviewDialogFragment:DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.popup_task_preview,container,false)

        //Initializing all the views of popup activity here
        val goalImage : ImageView = view.findViewById(R.id.imgTaskImage)
        val goalProgress : ProgressBar = view.findViewById(R.id.taskProgress)
        val updateValue : EditText = view.findViewById(R.id.edtValue)
        val btnPlus : ImageButton = view.findViewById(R.id.imgBtnPlus)
        val btnMinus : ImageButton = view.findViewById(R.id.imgBtnMinus)
        val btnCancel : TextView = view.findViewById(R.id.txtBtnCancel)
        val btnDone : TextView = view.findViewById(R.id.txtBtnDone)

        //Setting goal image here
        goalImage.setImageResource(R.drawable.img_meditation4x)

        //Setting the goal progress here
        goalProgress.setProgress(70,true)

        //Getting update value here and performing either plus or minus


        btnCancel.setOnClickListener {
            dismiss()
        }

        btnDone.setOnClickListener {
            dismiss()
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout((resources.displayMetrics.widthPixels*0.85).toInt(),ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
    }
}