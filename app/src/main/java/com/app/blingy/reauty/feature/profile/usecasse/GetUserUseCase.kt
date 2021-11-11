package com.app.blingy.reauty.feature.profile.usecasse

import com.app.blingy.reauty.core.domain.repository.UserRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private  val repo: UserRepository
) {
    suspend operator fun invoke() = repo.getUserProfile()

}