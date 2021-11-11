package com.app.blingy.reauty.feature.auth.domain.usecase

import com.app.blingy.reauty.core.domain.repository.AuthRepository
import com.google.firebase.auth.ActionCodeSettings
import javax.inject.Inject

class SendEmailLinkUseCase @Inject constructor(
    private val repo: AuthRepository
) {
    suspend operator fun invoke(email: String) =
        repo.sendSignInLinkToEmail(email)
}