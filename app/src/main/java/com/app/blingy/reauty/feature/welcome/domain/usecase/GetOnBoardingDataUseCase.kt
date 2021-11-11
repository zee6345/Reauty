package com.app.blingy.reauty.feature.welcome.domain.usecase

import android.content.Context
import com.app.blingy.reauty.R
import com.app.blingy.reauty.feature.welcome.domain.model.OnBoardingUiModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class GetOnBoardingDataUseCase @Inject constructor(
    @ApplicationContext context: Context
) {

    operator fun invoke(): List<OnBoardingUiModel> {
        return listOf<OnBoardingUiModel>(
            OnBoardingUiModel(
                title = R.string.text_onboarding_title_1,
                desc = R.string.text_onboarding_desc_1,
                image = R.raw.bee_welcome_1,
            ),
            OnBoardingUiModel(
                title = R.string.text_onboarding_title_2,
                desc = R.string.text_onboarding_desc_2,
                image = R.raw.bee_welcome_2,
            ),
            OnBoardingUiModel(
                title = R.string.text_onboarding_title_3,
                desc = R.string.text_onboarding_desc_3,
                image = R.raw.bee_welcome_3,
            ),
        )
    }

}