package com.app.blingy.reauty.feature.welcome.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.blingy.reauty.databinding.ItemWelcomeBinding
import com.app.blingy.reauty.feature.welcome.domain.model.OnBoardingUiModel

/**
 * to compare the Object is new or old to update the recycler view
 */
private val itemComparator = object : DiffUtil.ItemCallback<OnBoardingUiModel>() {
    override fun areItemsTheSame(
        oldItem: OnBoardingUiModel,
        newItem: OnBoardingUiModel
    ): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(
        oldItem: OnBoardingUiModel,
        newItem: OnBoardingUiModel
    ): Boolean {
        return oldItem == newItem
    }
}

class OnBoardingAdapter :
    ListAdapter<OnBoardingUiModel, OnBoardingAdapter.OnBoardingViewHolder>(itemComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        val view = ItemWelcomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OnBoardingViewHolder(view)
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class OnBoardingViewHolder(private val binding: ItemWelcomeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: OnBoardingUiModel) {
            val res = binding.root.context.resources
            binding.apply {
                tvTitle.text = res.getString(model.title).trim()
                tvDesc.text = res.getString(model.desc).trim()
                try {
                    animLottie.setAnimation(model.image)
                } catch (e: Exception) {

                }
            }
        }
    }

}