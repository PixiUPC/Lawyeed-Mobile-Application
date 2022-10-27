package com.example.lawyeed.search

import Beans.Cases
import Beans.Lawyer
import Helpers.CircleTransform
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lawyeed.R
import com.example.lawyeed.Search
import com.squareup.picasso.Picasso
import org.w3c.dom.Text

class LawyerSearchViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val names = view.findViewById<TextView>(R.id.rtext_names)
    val description = view.findViewById<TextView>(R.id.rtext_description)
    val image = view.findViewById<ImageView>(R.id.rimage_name)

    fun render(search: Lawyer){
        names.text = search.fisrtName.toString() + " " + search.lastName.toString()
        description.text = search.description.toString()
        Picasso.get()
            .load(search.urlImage.toString())
            .resize(42, 42)
            .centerCrop()
            .transform(CircleTransform())
            .into(image)
    }
}