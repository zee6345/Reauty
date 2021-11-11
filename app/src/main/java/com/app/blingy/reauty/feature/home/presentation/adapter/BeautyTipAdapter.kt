package com.app.blingy.reauty.feature.home.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.app.blingy.reauty.databinding.ItemBeautyTipBinding
import com.app.blingy.reauty.feature.home.domain.model.UiModelBeautyTip
import com.app.blingy.reauty.feature.home.presentation.adapter.viewholder.ItemBeautyTipViewHolder
import timber.log.Timber

/**
 * to compare the Object is new or old to update the recycler view
 */
private val itemComparator = object : DiffUtil.ItemCallback<UiModelBeautyTip>() {
    override fun areItemsTheSame(
        oldItem: UiModelBeautyTip,
        newItem: UiModelBeautyTip
    ): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(
        oldItem: UiModelBeautyTip,
        newItem: UiModelBeautyTip
    ): Boolean {
        return oldItem == newItem
    }
}

/**
 * adapter to populate the Beauty Tip Item
 */
class BeautyTipAdapter(
    private val onItemClicked: (position: Int) -> Unit
) : ListAdapter<UiModelBeautyTip, ItemBeautyTipViewHolder>(itemComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemBeautyTipViewHolder {
        val binding = ItemBeautyTipBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemBeautyTipViewHolder(binding, onItemClicked)
    }

    override fun onBindViewHolder(holder: ItemBeautyTipViewHolder, position: Int) {
        val currentItem: UiModelBeautyTip = getItem(position)
        holder.bind(currentItem)
    }

}