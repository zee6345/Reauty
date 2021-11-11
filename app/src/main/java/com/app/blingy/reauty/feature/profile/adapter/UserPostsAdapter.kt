package com.app.blingy.reauty.feature.profile.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.app.blingy.reauty.databinding.ItemFeedBinding
import com.app.blingy.reauty.feature.profile.model.UiModelPost

private val differCallback = object : DiffUtil.ItemCallback<UiModelPost>(){
    override fun areItemsTheSame(oldItem: UiModelPost, newItem: UiModelPost): Boolean {
        return  oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: UiModelPost, newItem: UiModelPost): Boolean {
        return oldItem.hashCode() === newItem.hashCode()
    }

}

class UserPostsAdapter(
    private val onItemFeedClickListener: OnItemFeedClickListener
        ): ListAdapter<UiModelPost,UserPostViewHolder>(differCallback ) {



//    fun submitList(list: List<UiModelPost>) = differ.submitList(list)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserPostViewHolder {
        val binding = ItemFeedBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return UserPostViewHolder(binding, onItemFeedClickListener)
    }


    override fun onBindViewHolder(holder: UserPostViewHolder, position: Int) {
        val currentItem : UiModelPost = getItem(position)
        holder.bind(currentItem)
    }


}