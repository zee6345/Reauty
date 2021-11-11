package com.app.blingy.reauty.feature.home.presentation.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.app.blingy.reauty.core.util.extension.setImageResId
import com.app.blingy.reauty.databinding.ItemBeautyTipBinding
import com.app.blingy.reauty.feature.home.domain.model.UiModelBeautyTip

/**
 * viewholder for the Beauty Tips Item
 */
class ItemBeautyTipViewHolder(
    private val binding: ItemBeautyTipBinding,
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

    fun bind(item: UiModelBeautyTip) {
        binding.apply {
            tvTitleItemBeautyTip.text = item.title.value
            imvImageItemBeautyTip.setImageResId(item.imageUrl)
        }
    }

}