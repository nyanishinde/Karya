package com.example.karya

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ImagePickerBottomSheetDialog(private val onImageSelected:(Int) -> Unit) : BottomSheetDialogFragment() {

    private val imageList=listOf(
        R.drawable.img_coding,R.drawable.img_gardening,R.drawable.img_study,
        R.drawable.img_journaling,R.drawable.img_cardio,R.drawable.img_meditation,
        R.drawable.img_cooking,R.drawable.img_savings,R.drawable.img_running
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.bottom_sheet_image_picker,container,false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewImages)

        recyclerView.layoutManager= GridLayoutManager(requireContext(),3)
        val adapter = AdapterImagePicker(imageList){ selectedImage->
            onImageSelected(selectedImage)
            dismiss()
        }
        recyclerView.adapter = adapter
        return view
    }

}