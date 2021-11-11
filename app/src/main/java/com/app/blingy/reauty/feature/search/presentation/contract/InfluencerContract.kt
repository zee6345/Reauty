package com.app.blingy.reauty.feature.search.presentation.contract

import com.app.blingy.reauty.core.presentation.mviBase.UiEvent
import com.app.blingy.reauty.core.presentation.mviBase.UiSideEffect
import com.app.blingy.reauty.core.presentation.mviBase.UiState

class InfluencerContract {

    sealed class InfluencerEvent : UiEvent {
        object HasSignIn : InfluencerEvent()
    }

    data class InfluencerViewState(
        val idle: Boolean = true,
        val isLoading: Boolean = false,
        val failure: Throwable? = null
    ) : UiState

    sealed class InfluencerSideEffect : UiSideEffect {
        data class ShowSnackBar(val message: String) : InfluencerSideEffect()
    }

}