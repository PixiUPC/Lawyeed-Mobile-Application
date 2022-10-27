package com.example.lawyeed.search

import Beans.Cases
import Beans.Lawyer
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lawyeed.R
import com.example.lawyeed.cases.CasesListViewHolder
import com.example.lawyeed.lawyers_home.LawyersHomeListViewHolder
import com.example.lawyeed.one_case

class Adapter(val lawyers:List<Lawyer>): RecyclerView.Adapter<LawyerSearchViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LawyerSearchViewHolder {

        val layoutInflater= LayoutInflater.from(parent.context)
        return LawyerSearchViewHolder(layoutInflater.inflate(R.layout.lawyers_search_item,parent,false))
    }

    override fun onBindViewHolder(holder: LawyerSearchViewHolder, position: Int) {
        val lawyer = lawyers[position]
        holder.render(lawyer)
    }

    override fun getItemCount(): Int = lawyers.size
}