package com.app.blingy.reauty.feature.auth.domain.usecase

import com.app.blingy.reauty.core.domain.repository.AuthRepository
import javax.inject.Inject

class IsSignInWithEmailLinkUseCase @Inject constructor(
    private val repo: AuthRepository
) {
    operator fun invoke(emailLink: String) =
        repo.isSignInWithEmailLink(emailLink)
}