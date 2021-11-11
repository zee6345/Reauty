package com.app.blingy.reauty.feature.dashboard.presentation.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.app.blingy.reauty.core.domain.model.introChat.ChatIntroLeft
import com.app.blingy.reauty.databinding.ItemChatRowLeftBinding

/**
 * viewholder for the intro chat left side
 */
class IntroChatLeftViewHolder(
    private val binding: ItemChatRowLeftBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ChatIntroLeft) {
        binding.apply {
            tvChatIntroLeft.text = item.text
            tvTimestampLeft.text = item.timeStamp
        }
    }

}