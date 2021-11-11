package com.app.blingy.reauty.feature.home.domain.usecase

import com.app.blingy.reauty.R
import com.app.blingy.reauty.feature.home.domain.model.UiModelBeautyTipDetail
import javax.inject.Inject

class GetAntiAgingTipsUseCase @Inject constructor() {
    operator fun invoke(): List<UiModelBeautyTipDetail> {
        return listOf(
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.anti_aging_1
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.anti_aging_2
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.anti_aging_3
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.anti_aging_4
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.anti_aging_5
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.anti_aging_6
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.anti_aging_7
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.anti_aging_8
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.anti_aging_9
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.anti_aging_10
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.anti_aging_11
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.anti_aging_12
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.anti_aging_13
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.anti_aging_14
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.anti_aging_15
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.anti_aging_16
            ),
        )
    }
}