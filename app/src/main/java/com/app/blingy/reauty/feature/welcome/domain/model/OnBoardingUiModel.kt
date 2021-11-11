package com.app.blingy.reauty.feature.welcome.domain.model

import androidx.annotation.RawRes
import androidx.annotation.StringRes

/**
 * to hold the data of onboarding process
 */
data class OnBoardingUiModel(
    @StringRes val title: Int = 0,
    @StringRes val desc: Int = 0,
    @RawRes val image: Int = 0
)
