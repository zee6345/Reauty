package com.app.blingy.reauty.feature.home.domain.usecase

import com.app.blingy.reauty.R
import com.app.blingy.reauty.feature.home.domain.model.UiModelBeautyTipDetail
import javax.inject.Inject

class GetCrueltyFreeTipsUseCase @Inject constructor() {
    operator fun invoke(): List<UiModelBeautyTipDetail> {
        return listOf(
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.cruelty_free_1
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.cruelty_free_2
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.cruelty_free_3
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.cruelty_free_4
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.cruelty_free_5
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.cruelty_free_6
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.cruelty_free_7
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.cruelty_free_8
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.cruelty_free_9
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.cruelty_free_10
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.cruelty_free_11
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.cruelty_free_12
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.cruelty_free_13
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.cruelty_free_14
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.cruelty_free_15
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.cruelty_free_16
            ),
        )
    }
}