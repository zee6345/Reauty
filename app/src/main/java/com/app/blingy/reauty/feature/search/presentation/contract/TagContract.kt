package com.app.blingy.reauty.feature.search.presentation.contract

import com.app.blingy.reauty.core.presentation.mviBase.UiEvent
import com.app.blingy.reauty.core.presentation.mviBase.UiSideEffect
import com.app.blingy.reauty.core.presentation.mviBase.UiState
import com.app.blingy.reauty.feature.search.domain.model.UiModelTag

class TagContract {

    sealed class TagEvent : UiEvent {
        object GetAllTags : TagEvent()
    }

    data class TagViewState(
        val idle: Boolean = true,
        val isLoading: Boolean = false,
        val tags: List<UiModelTag> = emptyList(),
        val failure: Throwable? = null
    ) : UiState

    sealed class TagSideEffect : UiSideEffect {
        data class ShowSnackBar(val message: String) : TagSideEffect()
    }

}