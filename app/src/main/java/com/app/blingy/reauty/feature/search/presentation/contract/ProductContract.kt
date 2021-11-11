package com.app.blingy.reauty.feature.search.presentation.contract

import com.app.blingy.reauty.core.presentation.mviBase.UiEvent
import com.app.blingy.reauty.core.presentation.mviBase.UiSideEffect
import com.app.blingy.reauty.core.presentation.mviBase.UiState

class ProductContract {

    sealed class ProductEvent : UiEvent {
        object HasSignIn : ProductEvent()
    }

    data class ProductViewState(
        val idle: Boolean = true,
        val isLoading: Boolean = false,
        val failure: Throwable? = null
    ) : UiState

    sealed class ProductSideEffect : UiSideEffect {
        data class ShowSnackBar(val message: String) : ProductSideEffect()
    }

}