package com.app.blingy.reauty.feature.home.domain.usecase

import com.app.blingy.reauty.R
import com.app.blingy.reauty.feature.home.domain.model.UiModelBeautyTipDetail
import javax.inject.Inject

class GetCleanBeautyTipsUseCase @Inject constructor() {
    operator fun invoke(): List<UiModelBeautyTipDetail> {
        return listOf(
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.clean_beauty_1
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.clean_beauty_2
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.clean_beauty_3
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.clean_beauty_4
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.clean_beauty_5
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.clean_beauty_6
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.clean_beauty_7
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.clean_beauty_8
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.clean_beauty_9
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.clean_beauty_10
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.clean_beauty_11
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.clean_beauty_12
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.clean_beauty_13
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.clean_beauty_14
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.clean_beauty_15
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.clean_beauty_16
            ),
        )
    }
}