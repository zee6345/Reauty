package com.app.blingy.reauty.feature.auth.presentation.contract

import android.content.Intent
import com.app.blingy.reauty.core.presentation.mviBase.UiEvent
import com.app.blingy.reauty.core.presentation.mviBase.UiSideEffect
import com.app.blingy.reauty.core.presentation.mviBase.UiState

class AuthContract {

    sealed class AuthEvent : UiEvent {
        data class SendEmailLink(val email: String) : AuthEvent()
        data class ShowEmailSentSuccessSnack(val email: String) : AuthEvent()
        data class ShowEmailSentFailureSnack(val email: String) : AuthEvent()
        data class PasswordLessSignIn(val email: String, val emailLink: String) : AuthEvent()
        data class ShowPasswordLessSuccessSnack(val userName: String) : AuthEvent()
        data class ShowPasswordLessFailureSnack(val message: String) : AuthEvent()
        object GetGoogleSignInIntent : AuthEvent()
        data class CredentialSignIn(val intent: Intent) : AuthEvent()
        data class ShowGoogleSignSuccessSnack(val userName: String) : AuthEvent()
        data class ShowGoogleSignErrorSnack(val userName: String) : AuthEvent()
    }

    data class AuthViewState(
        val idle: Boolean = true,
        val isLoading: Boolean = false,
        val isSignInFinished: Boolean = false,
        val emailAddress: String = "",
    ) : UiState

    sealed class AuthSideEffect : UiSideEffect {
        data class ShowSnackBar(val message: String) : AuthSideEffect()
    }

}