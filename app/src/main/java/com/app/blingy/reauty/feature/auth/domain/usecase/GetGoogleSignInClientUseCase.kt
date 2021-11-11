package com.app.blingy.reauty.feature.auth.domain.usecase

import com.app.blingy.reauty.core.domain.repository.AuthRepository
import javax.inject.Inject

class GetGoogleSignInClientUseCase @Inject constructor(
    private val repo: AuthRepository
) {
    operator fun invoke() =
        repo.getGoogleSignInIntent()
}