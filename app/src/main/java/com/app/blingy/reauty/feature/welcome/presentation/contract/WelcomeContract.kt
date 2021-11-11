package com.app.blingy.reauty.feature.welcome.presentation.contract

import com.app.blingy.reauty.core.presentation.mviBase.UiEvent
import com.app.blingy.reauty.core.presentation.mviBase.UiSideEffect
import com.app.blingy.reauty.core.presentation.mviBase.UiState
import com.app.blingy.reauty.feature.welcome.domain.model.OnBoardingUiModel

class WelcomeContract {

    sealed class WelcomeEvent : UiEvent {
        object HasSignIn : WelcomeEvent()
        object HasProfile : WelcomeEvent()
        object HasUniqueName : WelcomeEvent()
        object GetOnBoardingData: WelcomeEvent()
    }

    data class WelcomeViewState(
        val idle: Boolean = true,
        val isLoading: Boolean = false,
        val hasSignIn: Boolean = false,
        val hasProfile: Boolean = false,
        val hasUniqueName: Boolean = false,
        val onBoardingPageList: List<OnBoardingUiModel> = emptyList(),
        val failure: Throwable? = null
    ) : UiState

    sealed class WelcomeSideEffect : UiSideEffect {
        data class ShowSnackBar(val message: String) : WelcomeSideEffect()
    }

}