package com.app.blingy.reauty.feature.profile.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.blingy.reauty.R

class HeaderAdapter: RecyclerView.Adapter<HeaderAdapter.HeaderViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_profile_option_header, parent, false)
        return HeaderViewHolder(view)    }


    override fun getItemCount(): Int {
     return 1
    }
    class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val headerTextview: TextView = itemView.findViewById(R.id.item_headerPF)

        fun bind(header: String) {
            headerTextview.text = header
        }
    }

    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
//        holder.bind(header)
    }

}
