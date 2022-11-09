package com.example.lawyeed.lawyers_home

import Beans.Lawyer
import Helpers.CircleTransform
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lawyeed.R
import com.squareup.picasso.Picasso

class LawyersHomeListViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val name = view.findViewById<TextView>(R.id.text_name)
    val image = view.findViewById<ImageView>(R.id.image_name)

    fun render(lawyer:Lawyer){
        name.text = lawyer.fisrtName.toString()
        Picasso.get()
            .load(lawyer.urlImage.toString())
            .resize(42, 42)
            .centerCrop()
            .transform(CircleTransform())
            .into(image)


    }

}