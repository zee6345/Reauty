package com.app.blingy.reauty.feature.search.presentation.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.app.blingy.reauty.R
import com.app.blingy.reauty.core.domain.model.skinType.SkinType
import com.app.blingy.reauty.core.domain.model.skinType.skinTypeList
import com.app.blingy.reauty.core.util.extension.setImageResString
import com.app.blingy.reauty.databinding.ItemAccountBinding
import com.app.blingy.reauty.feature.search.domain.model.UiModelAccount


/**
 * viewholder for the Beauty Tips Item
 */
class ItemAccountViewHolder(
    private val binding: ItemAccountBinding,
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

    fun bind(item: UiModelAccount) {
        binding.apply {
            imvAvatar.setImageResString(item.imageUrl)
            tvName.text = item.userName
            tvCountReviews.text = item.reviewCount.toString()
            // to get the String of the SkinType
            val value = skinTypeList.find { it == item.skinTypeImage }
            // to get the match image url of the SkinType from SkinType enum
            val skinType = value?.let { SkinType.valueOf(it) }
            if (skinType != null){
                imvSkinType.setImageResource(skinType.imageUrl)
            }
            imvSkinCareFirst.setImageResource(R.drawable.ic_sc_talc_free)
            imvSkinCareSecond.setImageResource(R.drawable.ic_sc_alcohol_free)
            imvSkinCareThird.setImageResource(R.drawable.ic_sc_oil_free)
//            // to get the match String of SkinCardAbout
//            val skinCareFirstValue = skinCareList.find { it == item.skinCareImageFirst }
//            // to get the index number of SkinCardAbout
//            if (skinCareFirstValue != null || skinCareFirstValue != ""){
//                val index = skinCareList.indexOf(skinCareFirstValue)
//                // to get the image url that match the index from SkinCard enum
//                val skinCareImage = SkinCare.values()[index]
//                // set the image
//                imvSkinCareFirst.setImageResource(skinCareImage.imageUrl)
//            }
//
//            val skinCareSecondValue = skinCareList.find { it == item.skinCareImageSecond }
//            if (skinCareSecondValue != null || skinCareSecondValue != ""){
//                val index = skinCareList.indexOf(skinCareSecondValue)
//                // to get the image url that match the index from SkinCard enum
//                val skinCareImage = SkinCare.values()[index]
//                // set the image
//                imvSkinCareFirst.setImageResource(skinCareImage.imageUrl)
//            }
//
//            val skinCareThirdValue = skinCareList.find { it == item.skinCareImageThird }
//            if (skinCareThirdValue != null || skinCareThirdValue != ""){
//                val index = skinCareList.indexOf(skinCareThirdValue)
//                // to get the image url that match the index from SkinCard enum
//                val skinCareImage = SkinCare.values()[index]
//                // set the image
//                imvSkinCareFirst.setImageResource(skinCareImage.imageUrl)
//            }

        }
    }

}