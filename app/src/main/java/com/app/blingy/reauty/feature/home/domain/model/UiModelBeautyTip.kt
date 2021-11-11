package com.app.blingy.reauty.feature.home.domain.model

import com.app.blingy.reauty.core.domain.model.feed.BeautyTipType

//import kotlinx.parcelize.Parcelize

/**
 * model to hold the data of Beauty Tips
 */

data class UiModelBeautyTip(
    val title: BeautyTipType = BeautyTipType.ACNE_PRONE,
    val description: String = "",
    val imageUrl: Int = 0
)