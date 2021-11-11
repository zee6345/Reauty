package com.app.blingy.reauty.feature.auth.domain.usecase

import com.app.blingy.reauty.core.domain.repository.AuthRepository
import javax.inject.Inject

class SignInWithEmailLinkUseCase @Inject constructor(
    private val repo: AuthRepository
) {
    suspend operator fun invoke(email: String, emailLink: String) =
        repo.signInWithEmailLink(email, emailLink)
}