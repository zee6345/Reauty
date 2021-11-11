package com.app.blingy.reauty.feature.auth.domain.usecase

import com.app.blingy.reauty.core.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class SignInWithEmailUseCase @Inject constructor(
    private val repo: AuthRepository
) {

    suspend operator fun invoke(email: String, password: String): FirebaseUser? =
        repo.signInWithEmail(email, password)

}