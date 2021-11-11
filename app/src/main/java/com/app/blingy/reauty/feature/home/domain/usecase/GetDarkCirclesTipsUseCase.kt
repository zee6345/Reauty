package com.app.blingy.reauty.feature.home.domain.usecase

import com.app.blingy.reauty.R
import com.app.blingy.reauty.feature.home.domain.model.UiModelBeautyTipDetail
import javax.inject.Inject

class GetDarkCirclesTipsUseCase @Inject constructor() {
    operator fun invoke(): List<UiModelBeautyTipDetail> {
        return listOf(
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.dark_cycles_1
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.dark_cycles_2
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.dark_cycles_3
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.dark_cycles_4
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.dark_cycles_5
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.dark_cycles_6
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.dark_cycles_7
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.dark_cycles_8
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.dark_cycles_9
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.dark_cycles_10
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.dark_cycles_11
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.dark_cycles_12
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.dark_cycles_13
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.dark_cycles_14
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.dark_cycles_15
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.dark_cycles_16
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.dark_cycles_17
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.dark_cycles_18
            ),
        )
    }
}