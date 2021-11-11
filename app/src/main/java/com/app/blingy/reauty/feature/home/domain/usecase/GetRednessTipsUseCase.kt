package com.app.blingy.reauty.feature.home.domain.usecase

import com.app.blingy.reauty.R
import com.app.blingy.reauty.feature.home.domain.model.UiModelBeautyTipDetail
import javax.inject.Inject

class GetRednessTipsUseCase @Inject constructor() {
    operator fun invoke(): List<UiModelBeautyTipDetail> {
        return listOf(
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.redness_1
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.redness_2
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.redness_3
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.redness_4
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.redness_5
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.redness_6
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.redness_7
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.redness_8
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.redness_9
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.redness_10
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.redness_11
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.redness_12
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.redness_13
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.redness_14
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.redness_15
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.redness_16
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.redness_17
            ),
        )
    }
}