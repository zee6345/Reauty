package com.app.blingy.reauty.feature.search.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.app.blingy.reauty.databinding.ItemAccountBinding
import com.app.blingy.reauty.feature.search.domain.model.UiModelAccount
import com.app.blingy.reauty.feature.search.presentation.adapter.viewholder.ItemAccountViewHolder

/**
 * to compare the Object is new or old to update the recycler view
 */
private val itemComparator = object : DiffUtil.ItemCallback<UiModelAccount>() {
    override fun areItemsTheSame(
        oldItem: UiModelAccount,
        newItem: UiModelAccount
    ): Boolean {
        return oldItem.imageUrl == newItem.imageUrl
    }

    override fun areContentsTheSame(
        oldItem: UiModelAccount,
        newItem: UiModelAccount
    ): Boolean {
        return oldItem == newItem
    }
}

/**
 * adapter to populate the Account Item
 */
class ItemAccountAdapter(
    private val onItemClicked: (position: Int) -> Unit
) : ListAdapter<UiModelAccount, ItemAccountViewHolder>(itemComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAccountViewHolder {
        val binding = ItemAccountBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemAccountViewHolder(binding, onItemClicked)
    }

    override fun onBindViewHolder(holder: ItemAccountViewHolder, position: Int) {
        val currentItem: UiModelAccount = getItem(position)
        holder.bind(currentItem)
    }

}