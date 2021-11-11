package com.app.blingy.reauty.core.presentation.model.mapper

import com.app.blingy.reauty.core.domain.model.user.User
import com.app.blingy.reauty.feature.search.domain.model.UiModelAccount
import javax.inject.Inject

class AccountMapper @Inject constructor() : UiMapper<User, UiModelAccount> {
    override fun mapToView(input: User): UiModelAccount {
        return UiModelAccount(
            imageUrl = input.profilePicLink,
            userName = input.userSearchName,
            reviewCount = input.totalNumberOfReviews,
            skinTypeImage = input.skintype,
            skinCareImageFirst = if (input.skinCareAbout.isNotEmpty()) input.skinCareAbout[0] else "",
            skinCareImageSecond = if (input.skinCareAbout.isNotEmpty()) input.skinCareAbout[1] else "",
            skinCareImageThird = if (input.skinCareAbout.isNotEmpty()) input.skinCareAbout[2] else "",
        )
    }
}