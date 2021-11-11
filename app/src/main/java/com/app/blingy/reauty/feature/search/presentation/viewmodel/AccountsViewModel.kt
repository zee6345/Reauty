package com.app.blingy.reauty.feature.search.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.app.blingy.reauty.core.domain.model.user.User
import com.app.blingy.reauty.core.presentation.model.mapper.AccountMapper
import com.app.blingy.reauty.core.presentation.mviBase.BaseViewModel
import com.app.blingy.reauty.feature.search.domain.model.UiModelAccount
import com.app.blingy.reauty.feature.search.domain.usecase.account.GetAllAccountsUserCase
import com.app.blingy.reauty.feature.search.presentation.contract.AccountContract
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AccountsViewModel @Inject constructor(
    private val getAllAccountsUserCase: GetAllAccountsUserCase,
    private val accountMapper: AccountMapper
) : BaseViewModel<
        AccountContract.AccountEvent,
        AccountContract.AccountViewState,
        AccountContract.AccountSideEffect>() {

    override fun createInitialState(): AccountContract.AccountViewState {
        return AccountContract.AccountViewState(AccountContract.AccountViewState().idle)
    }

    override fun handleEvent(event: AccountContract.AccountEvent) {
        when (event) {
            AccountContract.AccountEvent.GetAllAccounts -> getAllAccounts()
            is AccountContract.AccountEvent.SearchAccount -> searchAccount(event.query)
        }
    }

    private fun getAllAccounts() {
        viewModelScope.launch {
            setUiState { copy(isLoading = true) }
            getAllAccountsUserCase()
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        Timber.d("getAllAccounts success, $snapshot")
                        val items = snapshot.children.map { ds ->
                            ds.getValue(User::class.java)
                        }
                        val uiItem = items.map {
                                accountMapper.mapToView(it!!)
                        }
                        setUiState {
                            Timber.d("getAllAccounts uiState, $uiItem")
                            copy(
                                isLoading = false,
                                accounts = uiItem
                            )
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Timber.d("getAllAccounts failure, ${error.details}")
                        setUiState {
                            copy(
                                isLoading = false,
                                failure = error.details
                            )
                        }
                    }
                })
        }
    }

    private fun searchAccount(query: String) {
        viewModelScope.launch {
            setUiState { copy(isLoading = true) }
            getAllAccountsUserCase()
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        snapshot.children.forEach { ds ->
                            val user = ds.getValue(User::class.java)
                            val dataSet: MutableList<UiModelAccount> = mutableListOf()
                            val searchList = dataSet.filter { it.userName.contains(query) }
                            user?.let { accountMapper.mapToView(it) }?.let { dataSet.add(it) }
                            setUiState {
                                copy(
                                    isLoading = false,
                                    accounts = searchList
                                )
                            }
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        setUiState {
                            copy(
                                isLoading = false,
                                failure = error.details
                            )
                        }
                    }
                })
        }
    }

    private fun idle() {
        Timber.d("idle, called")
    }
}