package com.app.blingy.reauty.feature.search.presentation.viewmodel

import com.app.blingy.reauty.core.presentation.mviBase.BaseViewModel
import com.app.blingy.reauty.feature.search.presentation.contract.InfluencerContract
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class InfluencersViewModel @Inject constructor() : BaseViewModel<
        InfluencerContract.InfluencerEvent,
        InfluencerContract.InfluencerViewState,
        InfluencerContract.InfluencerSideEffect>() {

    override fun createInitialState(): InfluencerContract.InfluencerViewState {
        return InfluencerContract.InfluencerViewState(InfluencerContract.InfluencerViewState().idle)
    }


    override fun handleEvent(event: InfluencerContract.InfluencerEvent) {
        when(event){
            InfluencerContract.InfluencerEvent.HasSignIn -> idle()
        }
    }


    private fun idle(){
        Timber.d("idle, called")
    }

}