package com.app.blingy.reauty.feature.home.presentation.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.app.blingy.reauty.core.util.extension.setImageResId
import com.app.blingy.reauty.databinding.ItemBeautyTipDetailBinding
import com.app.blingy.reauty.feature.home.domain.model.UiModelBeautyTipDetail

/**
 * viewholder for the Beauty Tips Item
 */
class ItemBeautyTipDetailViewHolder(
    private val binding: ItemBeautyTipDetailBinding,
    private val onItemClicked: (position: Int) -> Unit
) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val position = bindingAdapterPosition
        if (position != RecyclerView.NO_POSITION) {
            onItemClicked(position)
        }
    }

    fun bind(item: UiModelBeautyTipDetail) {
        binding.apply {
            imvImageItemBeautyTipDetail.setImageResId(item.imageUrl)
        }
    }

}