package com.app.blingy.reauty.feature.search.presentation.contract

import com.app.blingy.reauty.core.presentation.mviBase.UiEvent
import com.app.blingy.reauty.core.presentation.mviBase.UiSideEffect
import com.app.blingy.reauty.core.presentation.mviBase.UiState
import com.app.blingy.reauty.feature.search.domain.model.UiModelAccount

class AccountContract {

    sealed class AccountEvent : UiEvent {
        object GetAllAccounts : AccountEvent()
        data class SearchAccount(val query: String): AccountEvent()
    }

    data class AccountViewState(
        val idle: Boolean = true,
        val isLoading: Boolean = false,
        val accounts: List<UiModelAccount> = emptyList(),
        val failure: String? = null
    ) : UiState

    sealed class AccountSideEffect : UiSideEffect {
        data class ShowSnackBar(val message: String) : AccountSideEffect()
    }

}