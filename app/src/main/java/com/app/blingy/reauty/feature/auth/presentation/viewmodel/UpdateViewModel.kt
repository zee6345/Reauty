package com.app.blingy.reauty.feature.auth.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.app.blingy.reauty.core.data.preference.AppPreferences
import com.app.blingy.reauty.core.domain.model.user.User
import com.app.blingy.reauty.core.presentation.mviBase.BaseViewModel
import com.app.blingy.reauty.core.util.exhaustive
import com.app.blingy.reauty.feature.auth.domain.usecase.IsUniqueNameAvailableUseCase
import com.app.blingy.reauty.feature.auth.domain.usecase.UpdateUniqueNameUseCase
import com.app.blingy.reauty.feature.auth.domain.usecase.UpdateUserDataUseCase
import com.app.blingy.reauty.feature.auth.presentation.contract.CreateProfileContract
import com.google.firebase.FirebaseException
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class UpdateViewModel @Inject constructor(
    private val updateUserDataUseCase: UpdateUserDataUseCase,
    private val updateUniqueNameUseCase: UpdateUniqueNameUseCase,
    private val isUniqueNameAvailableUseCase: IsUniqueNameAvailableUseCase,
    private val dataStore: AppPreferences
) : BaseViewModel<
        CreateProfileContract.CreateProfileEvent,
        CreateProfileContract.CreateProfileViewState,
        CreateProfileContract.CreateProfileSideEffect
        >() {

    override fun createInitialState(): CreateProfileContract.CreateProfileViewState {
        return CreateProfileContract.CreateProfileViewState(
            CreateProfileContract.CreateProfileViewState().idle
        )
    }

    override fun handleEvent(event: CreateProfileContract.CreateProfileEvent) {
        when (event) {
            is CreateProfileContract.CreateProfileEvent.IsUniqueNameAvailable ->
                isUniqueNameAvailable(event.user)
            is CreateProfileContract.CreateProfileEvent.UpdateUniqueName ->
                updateUniqueName(event.user)
        }.exhaustive
    }

    fun updateUserData(user: User) {
        viewModelScope.launch { updateUserDataUseCase(user) }
    }

    private fun isUniqueNameAvailable(user: User) {
        viewModelScope.launch {
            try {
                setUiState { copy(isLoading = true) }
                isUniqueNameAvailableUseCase(user)
                    .addValueEventListener(object: ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            Timber.d("test success, $snapshot")
                            val users = snapshot.children.map {
                                it.getValue(User::class.java)
                            }
                            val value = users.find { it?.userSearchName == user.userSearchName }
                            Timber.d("test, $user")
                            if (value != null){
                                Timber.d("test, user is not null")
                                Timber.d("test, ${value.userName}")
                                Timber.d("test, ${value.userSearchName}")
                                setUiState {
                                    copy(
                                        isLoading = true,
                                        hasError = false,
                                        isUniqueNameAvailable = true
                                    )
                                }
                            }else{
                                Timber.d("test, user is null")
                            }
                        }

                        override fun onCancelled(error: DatabaseError) {
                            Timber.d("test failure, ${error.message}")
                            setUiState {
                            copy(
                                isLoading = false,
                                hasError = true,
                                errorMessage = error.message ?: "Something went wrong"
                            )
                        }
                        }
                    })
//                    .addOnFailureListener {
//                        setUiState {
//                            copy(
//                                isLoading = false,
//                                hasError = true,
//                                errorMessage = it.localizedMessage ?: "Something went wrong"
//                            )
//                        }
//                    }
//                    .addOnSuccessListener { dataSnapshot ->
//                        if (dataSnapshot.exists() && dataSnapshot.hasChildren()) {
//                            val result = dataSnapshot.getValue(User::class.java)
//                                .takeIf { it?.userSearchName == user.userSearchName }
//                            if (result != null) {
//                                setUiState {
//                                    copy(
//                                        isLoading = true,
//                                        hasError = false,
//                                        isUniqueNameAvailable = true
//                                    )
//                                }
//                            }
//                        }
//                    }
            } catch (e: FirebaseException) {
                setUiState {
                    copy(
                        isLoading = false,
                        hasError = true,
                        errorMessage = e.localizedMessage ?: "Something went wrong"
                    )
                }
            }
        }
    }

    private fun updateUniqueName(user: User) {
        viewModelScope.launch {
            updateUniqueNameUseCase(user)
            dataStore.setUniqueName(true)
        }
    }

}