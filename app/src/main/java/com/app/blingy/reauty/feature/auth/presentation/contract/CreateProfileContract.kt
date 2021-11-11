package com.app.blingy.reauty.feature.auth.presentation.contract

import com.app.blingy.reauty.core.domain.model.user.User
import com.app.blingy.reauty.core.presentation.mviBase.UiEvent
import com.app.blingy.reauty.core.presentation.mviBase.UiSideEffect
import com.app.blingy.reauty.core.presentation.mviBase.UiState

class CreateProfileContract {

    sealed class CreateProfileEvent : UiEvent {
        data class UpdateUniqueName(val user: User) : CreateProfileEvent()
        data class IsUniqueNameAvailable(val user: User) : CreateProfileEvent()
    }

    data class CreateProfileViewState(
        val idle: Boolean = true,
        val isLoading: Boolean = false,
        val isUniqueNameAvailable: Boolean = false,
        val hasError: Boolean = false,
        val errorMessage: String = ""
    ) : UiState

    sealed class CreateProfileSideEffect : UiSideEffect {
        data class ShowSnackBar(val message: String) : CreateProfileSideEffect()
    }

}