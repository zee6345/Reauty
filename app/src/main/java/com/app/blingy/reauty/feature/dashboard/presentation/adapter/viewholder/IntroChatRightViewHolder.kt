package com.app.blingy.reauty.feature.dashboard.presentation.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.app.blingy.reauty.core.domain.model.introChat.ChatIntroRight
import com.app.blingy.reauty.core.util.extension.setImageResId
import com.app.blingy.reauty.databinding.ItemChatRowRightBinding

/**
 * viewholder for the intro chat right side
 */
class IntroChatRightViewHolder(
    private val binding: ItemChatRowRightBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ChatIntroRight) {
        binding.apply {
            tvTimestampRight.text = item.text
            tvTimestampRight.text = item.timeStamp
            imvChatAvatar.setImageResId(item.image)
        }
    }

}