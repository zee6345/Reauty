package com.app.blingy.reauty.feature.search.presentation.viewmodel

import com.app.blingy.reauty.core.presentation.mviBase.BaseViewModel
import com.app.blingy.reauty.feature.search.presentation.contract.TagContract
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class TagsViewModel @Inject constructor() : BaseViewModel<
        TagContract.TagEvent,
        TagContract.TagViewState,
        TagContract.TagSideEffect>() {
    override fun createInitialState(): TagContract.TagViewState {
        return TagContract.TagViewState(TagContract.TagViewState().idle)
    }

    override fun handleEvent(event: TagContract.TagEvent) {
        when (event) {
            TagContract.TagEvent.GetAllTags -> idle()
        }
    }


    private fun idle(){
        Timber.d("idle, called")
    }

}