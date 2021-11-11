package com.app.blingy.reauty.feature.search.domain.usecase.account

import com.app.blingy.reauty.core.domain.repository.UserRepository
import javax.inject.Inject

class GetAllAccountsUserCase @Inject constructor(
    private val repo: UserRepository
) {

    operator fun invoke() = repo.getAllUsers()

}