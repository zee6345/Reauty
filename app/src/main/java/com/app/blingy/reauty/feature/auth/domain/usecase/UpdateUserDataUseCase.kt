package com.app.blingy.reauty.feature.auth.domain.usecase

import com.app.blingy.reauty.core.domain.model.user.User
import com.app.blingy.reauty.core.domain.repository.UserRepository
import javax.inject.Inject

class UpdateUserDataUseCase @Inject constructor(
    private val repo: UserRepository
) {
    operator fun invoke(user: User) = repo.updateUserData(user)
}