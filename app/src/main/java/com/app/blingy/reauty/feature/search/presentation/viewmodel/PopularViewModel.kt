package com.app.blingy.reauty.feature.search.presentation.viewmodel

import com.app.blingy.reauty.core.presentation.mviBase.BaseViewModel
import com.app.blingy.reauty.feature.search.presentation.contract.PopularContract
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject constructor() : BaseViewModel<
        PopularContract.PopularEvent,
        PopularContract.PopularViewState,
        PopularContract.PopularSideEffect>() {
    override fun createInitialState(): PopularContract.PopularViewState {
        return PopularContract.PopularViewState(PopularContract.PopularViewState().idle)
    }

    override fun handleEvent(event: PopularContract.PopularEvent) {
        when(event){
            PopularContract.PopularEvent.HasSignIn -> idle()
        }
    }


    private fun idle(){
        Timber.d("idle, called")
    }

}