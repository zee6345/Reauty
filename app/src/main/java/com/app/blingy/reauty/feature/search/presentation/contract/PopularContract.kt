package com.app.blingy.reauty.feature.search.presentation.contract

import com.app.blingy.reauty.core.presentation.mviBase.UiEvent
import com.app.blingy.reauty.core.presentation.mviBase.UiSideEffect
import com.app.blingy.reauty.core.presentation.mviBase.UiState

class PopularContract {

    sealed class PopularEvent : UiEvent {
        object HasSignIn : PopularEvent()
    }

    data class PopularViewState(
        val idle: Boolean = true,
        val isLoading: Boolean = false,
        val failure: Throwable? = null
    ) : UiState

    sealed class PopularSideEffect : UiSideEffect {
        data class ShowSnackBar(val message: String) : PopularSideEffect()
    }

}