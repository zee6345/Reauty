package com.app.blingy.reauty.feature.home.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.app.blingy.reauty.core.presentation.model.mapper.BeautyTipMapper
import com.app.blingy.reauty.core.presentation.model.mapper.FeedMapper
import com.app.blingy.reauty.core.presentation.mviBase.BaseViewModel
import com.app.blingy.reauty.core.util.exhaustive
import com.app.blingy.reauty.feature.home.domain.model.UiModelBeautyTip
import com.app.blingy.reauty.feature.home.domain.usecase.GetBeautyTipsUseCase
import com.app.blingy.reauty.feature.home.domain.usecase.GetFeedUseCase
import com.app.blingy.reauty.feature.home.presentation.contract.HomeContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getFeedUseCase: GetFeedUseCase,
    private val getBeautyTipsUseCase: GetBeautyTipsUseCase,
    private val beautyTipMapper: BeautyTipMapper,
    private val feedMapper: FeedMapper,
) : BaseViewModel<HomeContract.HomeEvent, HomeContract.HomeViewState, HomeContract.HomeSideEffect>() {

    override fun createInitialState(): HomeContract.HomeViewState {
        return HomeContract.HomeViewState(HomeContract.HomeViewState().idle)
    }

    override fun handleEvent(event: HomeContract.HomeEvent) {
        when (event) {
            HomeContract.HomeEvent.GetBeautyTips -> getBeautyTips()
            HomeContract.HomeEvent.GetFeeds -> getFeed()
            is HomeContract.HomeEvent.GiveComment -> addCommentToFeed(event.text)
            is HomeContract.HomeEvent.OnAvatarClicked -> gotoPostOwnerPage(event.id)
        }.exhaustive
    }

    private fun getBeautyTips() {
        viewModelScope.launch {
            setUiState { copy(isLoading = true) }
            val beautyList = mutableListOf<UiModelBeautyTip>()
            getBeautyTipsUseCase().forEach {
                beautyList.add(beautyTipMapper.mapToView(it))
            }
            setUiState {
                copy(
                    isLoading = false,
                    beautyTips = beautyList
                )
            }
        }
    }

    private fun getFeed() {
        viewModelScope.launch {
            getFeedUseCase()
                .catch { error ->
                    setSideEffect { HomeContract.HomeSideEffect.ShowSnackBar(error.message.toString()) }
                }.collect {
                    setUiState { copy(isLoading = true) }
                    when {
                        it.isFailure -> {
                            setUiState {
                                copy(
                                    isLoading = false,
                                    failure = it.exceptionOrNull()
                                )
                            }
                        }
                        it.isSuccess -> {
                            Timber.d("getFeed, ${it.getOrNull()}")
                            setUiState {
                                copy(
                                    isLoading = false,
                                    feedList = feedMapper.mapToView(it.getOrNull()!!)
                                )
                            }
                        }
                    }
                }
        }
    }

    private fun gotoPostOwnerPage(id: String){

    }

    private fun addCommentToFeed(text: String){

    }

}