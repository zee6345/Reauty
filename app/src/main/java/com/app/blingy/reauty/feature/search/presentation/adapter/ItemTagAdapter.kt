package com.app.blingy.reauty.feature.search.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.app.blingy.reauty.databinding.ItemTagBinding
import com.app.blingy.reauty.feature.search.domain.model.UiModelTag
import com.app.blingy.reauty.feature.search.presentation.adapter.viewholder.ItemTagViewHolder

/**
 * to compare the Object is new or old to update the recycler view
 */
private val itemComparator = object : DiffUtil.ItemCallback<UiModelTag>() {
    override fun areItemsTheSame(
        oldItem: UiModelTag,
        newItem: UiModelTag
    ): Boolean {
        return oldItem.imageUrl == newItem.imageUrl
    }

    override fun areContentsTheSame(
        oldItem: UiModelTag,
        newItem: UiModelTag
    ): Boolean {
        return oldItem == newItem
    }
}

/**
 * adapter to populate the Tag Item
 */
class ItemTagAdapter(
    private val onItemClicked: (position: Int) -> Unit
) : ListAdapter<UiModelTag, ItemTagViewHolder>(itemComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemTagViewHolder {
        val binding = ItemTagBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemTagViewHolder(binding, onItemClicked)
    }

    override fun onBindViewHolder(holder: ItemTagViewHolder, position: Int) {
        val currentItem: UiModelTag = getItem(position)
        holder.bind(currentItem)
    }

}