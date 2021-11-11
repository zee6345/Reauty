package com.app.blingy.reauty.feature.search.presentation.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.app.blingy.reauty.core.util.extension.setImageResString
import com.app.blingy.reauty.databinding.ItemTagBinding
import com.app.blingy.reauty.feature.search.domain.model.UiModelTag


/**
 * viewholder for the Tag Item
 */
class ItemTagViewHolder(
    private val binding: ItemTagBinding,
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

    fun bind(item: UiModelTag) {
        binding.apply {
            imvAvatar.setImageResString(item.imageUrl)
            tvCountPost.text = item.postCount.toString()
        }
    }

}