package com.app.blingy.reauty.feature.home.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.app.blingy.reauty.databinding.ItemBeautyTipDetailBinding
import com.app.blingy.reauty.feature.home.domain.model.UiModelBeautyTipDetail
import com.app.blingy.reauty.feature.home.presentation.adapter.viewholder.ItemBeautyTipDetailViewHolder

/**
 * to compare the Object is new or old to update the recycler view
 */
private val itemComparator = object : DiffUtil.ItemCallback<UiModelBeautyTipDetail>() {
    override fun areItemsTheSame(
        oldItem: UiModelBeautyTipDetail,
        newItem: UiModelBeautyTipDetail
    ): Boolean {
        return oldItem.imageUrl == newItem.imageUrl
    }

    override fun areContentsTheSame(
        oldItem: UiModelBeautyTipDetail,
        newItem: UiModelBeautyTipDetail
    ): Boolean {
        return oldItem == newItem
    }

}

/**
 * adapter to populate the Beauty Tip Detail Item
 */
class BeautyTipDetailAdapter(
    private val onItemClicked: (position: Int) -> Unit
) : ListAdapter<UiModelBeautyTipDetail, ItemBeautyTipDetailViewHolder>(itemComparator) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemBeautyTipDetailViewHolder {
        val binding = ItemBeautyTipDetailBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemBeautyTipDetailViewHolder(binding, onItemClicked)
    }

    override fun onBindViewHolder(holder: ItemBeautyTipDetailViewHolder, position: Int) {
        val currentItem: UiModelBeautyTipDetail = getItem(position)
        holder.bind(currentItem)
    }

}