package com.app.blingy.reauty.feature.search.presentation.viewmodel

import com.app.blingy.reauty.core.presentation.mviBase.BaseViewModel
import com.app.blingy.reauty.feature.search.presentation.contract.ProductContract
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor() : BaseViewModel<
        ProductContract.ProductEvent,
        ProductContract.ProductViewState,
        ProductContract.ProductSideEffect>() {
    override fun createInitialState(): ProductContract.ProductViewState {
        return ProductContract.ProductViewState(ProductContract.ProductViewState().idle)
    }

    override fun handleEvent(event: ProductContract.ProductEvent) {
        when (event) {
            ProductContract.ProductEvent.HasSignIn -> idle()
        }
    }


    private fun idle(){
        Timber.d("idle, called")
    }

}