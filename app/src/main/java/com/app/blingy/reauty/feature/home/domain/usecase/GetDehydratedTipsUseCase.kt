package com.app.blingy.reauty.feature.home.domain.usecase

import com.app.blingy.reauty.R
import com.app.blingy.reauty.feature.home.domain.model.UiModelBeautyTipDetail
import javax.inject.Inject

class GetDehydratedTipsUseCase @Inject constructor() {
    operator fun invoke(): List<UiModelBeautyTipDetail> {
        return listOf(
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.dehydrated_1
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.dehydrated_2
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.dehydrated_3
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.dehydrated_4
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.dehydrated_5
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.dehydrated_6
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.dehydrated_7
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.dehydrated_8
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.dehydrated_9
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.dehydrated_10
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.dehydrated_11
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.dehydrated_12
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.dehydrated_13
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.dehydrated_14
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.dehydrated_15
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.dehydrated_16
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.dehydrated_17
            ),
        )
    }
}