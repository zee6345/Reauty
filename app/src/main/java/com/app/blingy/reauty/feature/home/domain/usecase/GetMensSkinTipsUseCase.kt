package com.app.blingy.reauty.feature.home.domain.usecase

import com.app.blingy.reauty.R
import com.app.blingy.reauty.feature.home.domain.model.UiModelBeautyTipDetail
import javax.inject.Inject

class GetMensSkinTipsUseCase @Inject constructor() {
    operator fun invoke(): List<UiModelBeautyTipDetail> {
        return listOf(
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.mens_skin_1
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.mens_skin_2
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.mens_skin_3
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.mens_skin_4
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.mens_skin_5
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.mens_skin_6
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.mens_skin_7
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.mens_skin_8
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.mens_skin_9
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.mens_skin_10
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.mens_skin_11
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.mens_skin_12
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.mens_skin_13
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.mens_skin_14
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.mens_skin_15
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.mens_skin_16
            ),
        )
    }
}