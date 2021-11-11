package com.app.blingy.reauty.feature.auth.domain.usecase

import com.app.blingy.reauty.core.domain.repository.AuthRepository
import com.google.firebase.auth.AuthCredential
import javax.inject.Inject

class SignInWithCredentialUseCase @Inject constructor(
    private val repo: AuthRepository,
) {

    suspend operator fun invoke(credential: AuthCredential) =
        repo.signInWithCredential(credential)


}