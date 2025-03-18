package com.example.karya

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class AdapterImagePicker(private val imageList: List<Int>,private val onImageClick:(Int)-> Unit):
    RecyclerView.Adapter<AdapterImagePicker.PickerViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PickerViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_image,parent,false)
        return PickerViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: PickerViewHolder,
        position: Int
    ) {
        holder.bind(imageList[position])
    }

    override fun getItemCount() = imageList.size

    inner class PickerViewHolder(view: View): RecyclerView.ViewHolder(view){

        val imageView: ImageView = view.findViewById(R.id.imageViewItem)
        fun bind(imageRes: Int) {
            imageView.setImageResource(imageRes)
            itemView.setOnClickListener { onImageClick(imageRes) }
        }
    }

}