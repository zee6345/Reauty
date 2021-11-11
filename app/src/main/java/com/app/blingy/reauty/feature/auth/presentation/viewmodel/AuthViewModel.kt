package com.app.blingy.reauty.feature.auth.presentation.viewmodel

import android.content.Intent
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.app.blingy.reauty.core.data.preference.AppPreferences
import com.app.blingy.reauty.core.domain.model.user.User
import com.app.blingy.reauty.core.presentation.model.mapper.SignInUserMapper
import com.app.blingy.reauty.core.presentation.mviBase.BaseViewModel
import com.app.blingy.reauty.core.util.ResultOf
import com.app.blingy.reauty.core.util.exhaustive
import com.app.blingy.reauty.feature.auth.domain.usecase.*
import com.app.blingy.reauty.feature.auth.presentation.contract.AuthContract
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.GoogleAuthProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val signInUserMapper: SignInUserMapper,
    private val sendEmailLinkUseCase: SendEmailLinkUseCase,
    private val isSignInWithEmailLinkUseCase: IsSignInWithEmailLinkUseCase,
    private val signInWithEmailLinkUseCase: SignInWithEmailLinkUseCase,
    private val getGoogleSignInClientUseCase: GetGoogleSignInClientUseCase,
    private val signInWithCredentialUseCase: SignInWithCredentialUseCase,
    private val createUserUseCase: CreateUserUseCase,
    private val appPreferences: AppPreferences,
) :
    BaseViewModel<AuthContract.AuthEvent, AuthContract.AuthViewState, AuthContract.AuthSideEffect>() {


    val TAG = AuthViewModel::class.simpleName

    override fun createInitialState(): AuthContract.AuthViewState {
        return AuthContract.AuthViewState(AuthContract.AuthViewState().idle)
    }

    override fun handleEvent(event: AuthContract.AuthEvent) {
        when (event) {
            is AuthContract.AuthEvent.CredentialSignIn -> signInWithCredential(event.intent)
            AuthContract.AuthEvent.GetGoogleSignInIntent -> getGoogleSignInIntent()
            is AuthContract.AuthEvent.PasswordLessSignIn -> signInWithEmailLink(
                event.email,
                event.emailLink
            )
            is AuthContract.AuthEvent.SendEmailLink -> sendEmailLink(event.email)
            is AuthContract.AuthEvent.ShowEmailSentFailureSnack -> showSnackWithMessage(event.email)
            is AuthContract.AuthEvent.ShowEmailSentSuccessSnack -> showSnackWithMessage(event.email)
            is AuthContract.AuthEvent.ShowGoogleSignErrorSnack -> showSnackWithMessage(event.userName)
            is AuthContract.AuthEvent.ShowGoogleSignSuccessSnack -> showSnackWithMessage(event.userName)
            is AuthContract.AuthEvent.ShowPasswordLessFailureSnack -> showSnackWithMessage(event.message)
            is AuthContract.AuthEvent.ShowPasswordLessSuccessSnack -> showSnackWithMessage(event.userName)
        }
    }

    private fun sendEmailLink(email: String) {
        viewModelScope.launch {
            try {
                setUiState { copy(isLoading = true) }
                val response = sendEmailLinkUseCase(email)
                when (response) {
                    is ResultOf.Failure -> showSnackWithMessage("Something went wrong!")
                    is ResultOf.Success -> {
                        appPreferences.setEmailAddress(email)
                        setUiState { copy(isLoading = false) }
                        showSnackWithMessage("Please check your email")
                    }
                }.exhaustive
            } catch (e: FirebaseAuthException) {
                showSnackWithMessage(e.errorCode)
            }
        }
    }

    fun getEmailAddress() {
        viewModelScope.launch {
            appPreferences.getEmailAddress().collect {
                setUiState {
                    copy(
                        isLoading = false,
                        emailAddress = it
                    )
                }
            }
        }
    }

    fun isSignInWithEmailLink(emailLink: String) = isSignInWithEmailLinkUseCase(emailLink)

    fun signInWithEmailLink(email: String, emailLink: String) {
        viewModelScope.launch {
            try {
                setUiState { copy(isLoading = true) }
                val response = signInWithEmailLinkUseCase(email, emailLink)
                when (response) {
                    is ResultOf.Failure -> showSnackWithMessage("Something went wrong!")
                    is ResultOf.Success -> {
                        setUiState {
                            copy(
                                isLoading = false,
                                isSignInFinished = true
                            )
                        }
                        showSnackWithMessage("Sign In Success ${response.data.displayName}")
                    }
                }.exhaustive
            } catch (e: FirebaseAuthException) {
                showSnackWithMessage(e.errorCode)
            }
        }
    }

    fun getGoogleSignInIntent() = getGoogleSignInClientUseCase()

    private fun signInWithCredential(intent: Intent) {
        viewModelScope.launch {
            try {
                val googleSignInAccount = GoogleSignIn.getSignedInAccountFromIntent(intent).result
                val credential = GoogleAuthProvider.getCredential(googleSignInAccount.idToken, null)
                val firebaseUser = signInWithCredentialUseCase(credential)
                if (firebaseUser != null) {
                    setUiState {
                        copy(
                            isLoading = false,
                            isSignInFinished = true
                        )
                    }
                    createUser(signInUserMapper.mapToView(firebaseUser))
                }
                showSnackWithMessage("Welcome ${firebaseUser?.displayName}")
            } catch (e: FirebaseAuthException) {
                showSnackWithMessage(e.errorCode)
            } catch (e1: ApiException) {
                showSnackWithMessage(e1.statusCode.toString())
            }
        }

    }

    private fun showSnackWithMessage(value: String) {
        setSideEffect { AuthContract.AuthSideEffect.ShowSnackBar(value) }
    }

    private fun createUser(user: User) {
        viewModelScope.launch { createUserUseCase(user) }
    }

}