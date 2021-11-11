package com.app.blingy.reauty.feature.profile.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.blingy.reauty.R
import com.app.blingy.reauty.feature.profile.utils.OptionItems

private val items: List<OptionItems>? = null
private val listener: CustomAdapter.OnItemClickListener? = null


class CustomAdapter(private val onItemClicked: (OptionItems) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    val differCallback = object : DiffUtil.ItemCallback<OptionItems>(){
        override fun areItemsTheSame(oldItem: OptionItems, newItem: OptionItems): Boolean {
            return  oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: OptionItems, newItem: OptionItems): Boolean {
            return oldItem.hashCode() === newItem.hashCode()
        }

    }

    val differ = AsyncListDiffer(this, differCallback)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_profile_option,
                parent,
                false
            )
        )
    }


    fun submitList(list: List<OptionItems>) = differ.submitList(list)

    inner class ViewHolder(private var itemview: View) :
        RecyclerView.ViewHolder(itemview)

    override fun getItemCount(): Int {
        return differ.currentList.size
    }




    interface OnItemClickListener {
        fun onItemClick(item:OptionItems)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val items = differ.currentList[position]
        holder.itemView.findViewById<TextView>(R.id.item_options).text = items.Items
        holder.itemView.setOnClickListener{
            onItemClicked(items)
        }

    }


}