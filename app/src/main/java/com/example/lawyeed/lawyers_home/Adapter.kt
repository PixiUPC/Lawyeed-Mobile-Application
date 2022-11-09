package com.example.lawyeed.lawyers_home

import Beans.Cases
import Beans.Lawyer
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lawyeed.R

class Adapter(val lawyers:List<Lawyer>): RecyclerView.Adapter<LawyersHomeListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LawyersHomeListViewHolder {

        val layoutInflater= LayoutInflater.from(parent.context)
        return LawyersHomeListViewHolder(layoutInflater.inflate(R.layout.lawyers_home_item,parent,false))
    }

    override fun onBindViewHolder(holder: LawyersHomeListViewHolder, position: Int) {
        val lawyer = lawyers[position]
        holder.render(lawyer)
    }

    override fun getItemCount(): Int = lawyers.size
}