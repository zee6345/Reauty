package com.app.blingy.reauty.feature.home.domain.usecase

import com.app.blingy.reauty.R
import com.app.blingy.reauty.feature.home.domain.model.UiModelBeautyTipDetail
import javax.inject.Inject

class GetLargePoleTipsUseCase @Inject constructor() {
    operator fun invoke(): List<UiModelBeautyTipDetail> {
        return listOf(
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.large_poles_1
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.large_poles_2
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.large_poles_3
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.large_poles_4
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.large_poles_5
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.large_poles_6
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.large_poles_7
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.large_poles_8
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.large_poles_9
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.large_poles_10
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.large_poles_11
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.large_poles_12
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.large_poles_13
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.large_poles_14
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.large_poles_15
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.large_poles_16
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.large_poles_17
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.large_poles_18
            ),
        )
    }
}