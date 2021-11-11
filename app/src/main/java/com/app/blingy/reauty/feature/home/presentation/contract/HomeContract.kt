package com.app.blingy.reauty.feature.home.presentation.contract

import com.app.blingy.reauty.core.presentation.mviBase.UiEvent
import com.app.blingy.reauty.core.presentation.mviBase.UiSideEffect
import com.app.blingy.reauty.core.presentation.mviBase.UiState
import com.app.blingy.reauty.feature.home.domain.model.UiModelBeautyTip
import com.app.blingy.reauty.feature.home.domain.model.UiModelFeed

class HomeContract {

    sealed class HomeEvent : UiEvent {
        object GetBeautyTips : HomeEvent()
        object GetFeeds : HomeEvent()
        data class OnAvatarClicked(val id: String): HomeEvent()
        data class GiveComment(val text: String): HomeEvent()
    }

    data class HomeViewState(
        val idle: Boolean = true,
        val isLoading: Boolean = false,
        val beautyTips: List<UiModelBeautyTip> = emptyList(),
        val feedList: List<UiModelFeed> = emptyList(),
        val failure: Throwable? = null
    ) : UiState

    sealed class HomeSideEffect : UiSideEffect {
        data class ShowSnackBar(val message: String) : HomeSideEffect()
    }

}