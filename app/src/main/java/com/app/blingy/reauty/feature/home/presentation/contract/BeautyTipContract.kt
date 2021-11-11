package com.app.blingy.reauty.feature.home.presentation.contract

import com.app.blingy.reauty.core.presentation.mviBase.UiEvent
import com.app.blingy.reauty.core.presentation.mviBase.UiSideEffect
import com.app.blingy.reauty.core.presentation.mviBase.UiState
import com.app.blingy.reauty.feature.home.domain.model.UiModelBeautyTipDetail

class BeautyTipContract {

    sealed class BeautyTipEvent : UiEvent {
        object GetAcneProneTips : BeautyTipEvent()
        object GetAntiAgingTips : BeautyTipEvent()
        object GetCleanBeautyTips : BeautyTipEvent()
        object GetCrueltyFreeTips : BeautyTipEvent()
        object GetDarkCirclesTips : BeautyTipEvent()
        object GetDehydratedTips : BeautyTipEvent()
        object GetDullSkinTips : BeautyTipEvent()
        object GetLargePoresTips : BeautyTipEvent()
        object GetMensSkinTips : BeautyTipEvent()
        object GetNaturalBeautyTips : BeautyTipEvent()
        object GetRednessTips : BeautyTipEvent()
        object GetVeganBeautyTips : BeautyTipEvent()
    }

    data class BeautyTipViewState(
        val idle: Boolean = true,
        val isLoading: Boolean = false,
        val beautyTips: List<UiModelBeautyTipDetail> = emptyList(),
        val failure: Throwable? = null
    ) : UiState

    sealed class BeautyTipSideEffect : UiSideEffect {
        data class ShowSnackBar(val message: String) : BeautyTipSideEffect()
    }

}