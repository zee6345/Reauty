package com.app.blingy.reauty.feature.home.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.app.blingy.reauty.databinding.ItemFeedBinding
import com.app.blingy.reauty.feature.home.domain.model.UiModelFeed
import com.app.blingy.reauty.feature.home.presentation.adapter.viewholder.ItemFeedViewHolder
import com.app.blingy.reauty.feature.home.presentation.adapter.viewholder.OnItemFeedClickListener

/**
 * to compare the Object is new or old to update the recycler view
 */
private val itemComparator = object : DiffUtil.ItemCallback<UiModelFeed>() {
    override fun areItemsTheSame(
        oldItem: UiModelFeed,
        newItem: UiModelFeed
    ): Boolean {
        return oldItem.postOwnerId == newItem.postOwnerId
    }

    override fun areContentsTheSame(
        oldItem: UiModelFeed,
        newItem: UiModelFeed
    ): Boolean {
        return oldItem == newItem
    }

}

/**
 * adapter to populate the Feed Item
 */
class FeedAdapter(
    private val onItemFeedClickListener: OnItemFeedClickListener
) : ListAdapter<UiModelFeed, ItemFeedViewHolder>(itemComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemFeedViewHolder {
        val binding = ItemFeedBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemFeedViewHolder(binding, onItemFeedClickListener)
    }

    override fun onBindViewHolder(holder: ItemFeedViewHolder, position: Int) {
        val currentItem: UiModelFeed = getItem(position)
        holder.bind(currentItem)
    }

}