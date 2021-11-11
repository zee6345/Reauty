package com.app.blingy.reauty.feature.home.domain.usecase

import com.app.blingy.reauty.R
import com.app.blingy.reauty.feature.home.domain.model.UiModelBeautyTipDetail
import javax.inject.Inject

class GetAcneProneTipsUseCase @Inject constructor() {
    operator fun invoke(): List<UiModelBeautyTipDetail> {
        return listOf(
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.acne_prone_1
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.acne_prone_2
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.acne_prone_3
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.acne_prone_4
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.acne_prone_5
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.acne_prone_6
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.acne_prone_7
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.acne_prone_8
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.acne_prone_9
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.acne_prone_10
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.acne_prone_11
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.acne_prone_12
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.acne_prone_13
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.acne_prone_14
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.acne_prone_15
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.acne_prone_16
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.acne_prone_17
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.acne_prone_18
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.acne_prone_19
            ),
            UiModelBeautyTipDetail(
                imageUrl = R.drawable.acne_prone_20
            ),
        )
    }
}