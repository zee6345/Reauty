package com.app.blingy.reauty.feature.welcome.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.app.blingy.reauty.core.data.preference.AppPreferences
import com.app.blingy.reauty.core.presentation.mviBase.BaseViewModel
import com.app.blingy.reauty.core.util.exhaustive
import com.app.blingy.reauty.feature.welcome.domain.usecase.GetOnBoardingDataUseCase
import com.app.blingy.reauty.feature.welcome.presentation.contract.WelcomeContract
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val auth: FirebaseAuth,
    private val dataStore: AppPreferences,
    private val getOnBoardingDataUseCase: GetOnBoardingDataUseCase
) : BaseViewModel<
        WelcomeContract.WelcomeEvent,
        WelcomeContract.WelcomeViewState,
        WelcomeContract.WelcomeSideEffect>() {
    override fun createInitialState() = WelcomeContract.WelcomeViewState(
        WelcomeContract.WelcomeViewState().idle
    )

    override fun handleEvent(event: WelcomeContract.WelcomeEvent) {
        when (event) {
            WelcomeContract.WelcomeEvent.HasSignIn -> hasSignIn()
            WelcomeContract.WelcomeEvent.HasProfile -> hasProfile()
            WelcomeContract.WelcomeEvent.HasUniqueName -> hasUniqueName()
            WelcomeContract.WelcomeEvent.GetOnBoardingData -> getOnBoardingData()
        }.exhaustive
    }

    private fun hasSignIn() {
        viewModelScope.launch {
            if (auth.currentUser != null) {
                setUiState { copy(hasSignIn = true) }
            } else {
                setUiState { copy(hasSignIn = false) }
            }
        }
    }

    private fun hasProfile() {
        viewModelScope.launch {
            dataStore.hasCreateProfile().collect { hasProfile ->
                if (hasProfile) {
                    setUiState { copy(hasProfile = true) }
                } else setUiState { copy(hasProfile = true) }
            }
        }
    }

    private fun hasUniqueName() {
        viewModelScope.launch {
            dataStore.clearDataStore()
            dataStore.hasUniqueName().collect { hasUniqueName ->
                if (hasUniqueName) {
                    setUiState { copy(hasUniqueName = true) }
                } else setUiState { copy(hasUniqueName = true) }
            }
        }
    }

    private fun getOnBoardingData() {
        viewModelScope.launch {
            val dataSet = getOnBoardingDataUseCase()
            setUiState { copy(onBoardingPageList = dataSet) }
        }
    }

}