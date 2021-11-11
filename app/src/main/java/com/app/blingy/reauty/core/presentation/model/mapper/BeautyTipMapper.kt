package com.app.blingy.reauty.core.presentation.model.mapper

import com.app.blingy.reauty.core.domain.model.feed.BeautyTip
import com.app.blingy.reauty.feature.home.domain.model.UiModelBeautyTip
import javax.inject.Inject

class BeautyTipMapper @Inject constructor() : UiMapper<BeautyTip, UiModelBeautyTip> {
    override fun mapToView(input: BeautyTip): UiModelBeautyTip {
        return UiModelBeautyTip(
            title = input.title,
            description = input.description,
            imageUrl = input.imageUrl
        )
    }
}