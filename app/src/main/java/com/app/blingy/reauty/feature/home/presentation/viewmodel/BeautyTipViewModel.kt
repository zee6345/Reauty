package com.app.blingy.reauty.feature.home.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.app.blingy.reauty.core.presentation.mviBase.BaseViewModel
import com.app.blingy.reauty.core.util.exhaustive
import com.app.blingy.reauty.feature.home.domain.model.UiModelBeautyTipDetail
import com.app.blingy.reauty.feature.home.domain.usecase.*
import com.app.blingy.reauty.feature.home.presentation.contract.BeautyTipContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BeautyTipViewModel @Inject constructor(
    private val getAcneProneTipsUseCase: GetAcneProneTipsUseCase,
    private val getAntiAgingTipsUseCase: GetAntiAgingTipsUseCase,
    private val getCleanBeautyTipsUseCase: GetCleanBeautyTipsUseCase,
    private val getCrueltyFreeTipsUseCase: GetCrueltyFreeTipsUseCase,
    private val getDarkCirclesTipsUseCase: GetDarkCirclesTipsUseCase,
    private val getDehydratedTipsUseCase: GetDehydratedTipsUseCase,
    private val getDullSkinTipsUseCase: GetDullSkinTipsUseCase,
    private val getLargePoleTipsUseCase: GetLargePoleTipsUseCase,
    private val getMensSkinTipsUseCase: GetMensSkinTipsUseCase,
    private val getNaturalBeautyTipsUseCase: GetNaturalBeautyTipsUseCase,
    private val getRednessTipsUseCase: GetRednessTipsUseCase,
    private val getVeganBeautyTipsUseCase: GetVeganBeautyTipsUseCase
) : BaseViewModel<BeautyTipContract.BeautyTipEvent, BeautyTipContract.BeautyTipViewState, BeautyTipContract.BeautyTipSideEffect>() {

    override fun createInitialState(): BeautyTipContract.BeautyTipViewState {
        return BeautyTipContract.BeautyTipViewState(BeautyTipContract.BeautyTipViewState().idle)
    }

    override fun handleEvent(event: BeautyTipContract.BeautyTipEvent) {
        when (event) {
            BeautyTipContract.BeautyTipEvent.GetAcneProneTips -> getAcneProneTips()
            BeautyTipContract.BeautyTipEvent.GetAntiAgingTips -> getAntiAgingTips()
            BeautyTipContract.BeautyTipEvent.GetCrueltyFreeTips -> getCrueltyFreeTips()
            BeautyTipContract.BeautyTipEvent.GetDarkCirclesTips -> getDarkCirclesTips()
            BeautyTipContract.BeautyTipEvent.GetDehydratedTips -> getDehydratedTips()
            BeautyTipContract.BeautyTipEvent.GetDullSkinTips -> getDullSkinTips()
            BeautyTipContract.BeautyTipEvent.GetLargePoresTips -> getLargePoresTips()
            BeautyTipContract.BeautyTipEvent.GetMensSkinTips -> getMensSkinTips()
            BeautyTipContract.BeautyTipEvent.GetNaturalBeautyTips -> getNaturalBeautyTips()
            BeautyTipContract.BeautyTipEvent.GetRednessTips -> getRednessTips()
            BeautyTipContract.BeautyTipEvent.GetVeganBeautyTips -> getVeganBeautyTips()
            BeautyTipContract.BeautyTipEvent.GetCleanBeautyTips -> getCleanBeautyTips()
        }.exhaustive
    }

    private fun getAcneProneTips() {
        viewModelScope.launch {
            setUiState { copy(isLoading = true) }
            val dataSet = mutableListOf<UiModelBeautyTipDetail>()
            getAcneProneTipsUseCase().forEach {
                dataSet.add(it)
            }
            setUiState {
                copy(
                    isLoading = false,
                    beautyTips = dataSet
                )
            }
        }
    }

    private fun getAntiAgingTips() {
        viewModelScope.launch {
            setUiState { copy(isLoading = true) }
            val dataSet = mutableListOf<UiModelBeautyTipDetail>()
            getAntiAgingTipsUseCase().forEach {
                dataSet.add(it)
            }
            setUiState {
                copy(
                    isLoading = false,
                    beautyTips = dataSet
                )
            }
        }
    }

    private fun getCrueltyFreeTips() {
        viewModelScope.launch {
            setUiState { copy(isLoading = true) }
            val dataSet = mutableListOf<UiModelBeautyTipDetail>()
            getCrueltyFreeTipsUseCase().forEach {
                dataSet.add(it)
            }
            setUiState {
                copy(
                    isLoading = false,
                    beautyTips = dataSet
                )
            }
        }
    }

    private fun getDarkCirclesTips() {
        viewModelScope.launch {
            setUiState { copy(isLoading = true) }
            val dataSet = mutableListOf<UiModelBeautyTipDetail>()
            getDarkCirclesTipsUseCase().forEach {
                dataSet.add(it)
            }
            setUiState {
                copy(
                    isLoading = false,
                    beautyTips = dataSet
                )
            }
        }
    }

    private fun getDehydratedTips() {
        viewModelScope.launch {
            setUiState { copy(isLoading = true) }
            val dataSet = mutableListOf<UiModelBeautyTipDetail>()
            getDehydratedTipsUseCase().forEach {
                dataSet.add(it)
            }
            setUiState {
                copy(
                    isLoading = false,
                    beautyTips = dataSet
                )
            }
        }
    }

    private fun getDullSkinTips() {
        viewModelScope.launch {
            setUiState { copy(isLoading = true) }
            val dataSet = mutableListOf<UiModelBeautyTipDetail>()
            getDullSkinTipsUseCase().forEach {
                dataSet.add(it)
            }
            setUiState {
                copy(
                    isLoading = false,
                    beautyTips = dataSet
                )
            }
        }
    }

    private fun getLargePoresTips() {
        viewModelScope.launch {
            setUiState { copy(isLoading = true) }
            val dataSet = mutableListOf<UiModelBeautyTipDetail>()
            getLargePoleTipsUseCase().forEach {
                dataSet.add(it)
            }
            setUiState {
                copy(
                    isLoading = false,
                    beautyTips = dataSet
                )
            }
        }
    }

    private fun getMensSkinTips() {
        viewModelScope.launch {
            setUiState { copy(isLoading = true) }
            val dataSet = mutableListOf<UiModelBeautyTipDetail>()
            getMensSkinTipsUseCase().forEach {
                dataSet.add(it)
            }
            setUiState {
                copy(
                    isLoading = false,
                    beautyTips = dataSet
                )
            }
        }
    }

    private fun getNaturalBeautyTips() {
        viewModelScope.launch {
            setUiState { copy(isLoading = true) }
            val dataSet = mutableListOf<UiModelBeautyTipDetail>()
            getNaturalBeautyTipsUseCase().forEach {
                dataSet.add(it)
            }
            setUiState {
                copy(
                    isLoading = false,
                    beautyTips = dataSet
                )
            }
        }
    }

    private fun getRednessTips() {
        viewModelScope.launch {
            setUiState { copy(isLoading = true) }
            val dataSet = mutableListOf<UiModelBeautyTipDetail>()
            getRednessTipsUseCase().forEach {
                dataSet.add(it)
            }
            setUiState {
                copy(
                    isLoading = false,
                    beautyTips = dataSet
                )
            }
        }
    }

    private fun getVeganBeautyTips() {
        viewModelScope.launch {
            setUiState { copy(isLoading = true) }
            val dataSet = mutableListOf<UiModelBeautyTipDetail>()
            getVeganBeautyTipsUseCase().forEach {
                dataSet.add(it)
            }
            setUiState {
                copy(
                    isLoading = false,
                    beautyTips = dataSet
                )
            }
        }
    }

    private fun getCleanBeautyTips() {
        viewModelScope.launch {
            setUiState { copy(isLoading = true) }
            val dataSet = mutableListOf<UiModelBeautyTipDetail>()
            getCleanBeautyTipsUseCase().forEach {
                dataSet.add(it)
            }
            setUiState {
                copy(
                    isLoading = false,
                    beautyTips = dataSet
                )
            }
        }
    }

}