package com.app.blingy.reauty.core.presentation.model.mapper

import com.app.blingy.reauty.core.domain.model.user.User
import com.app.blingy.reauty.feature.auth.domain.model.UiModelSignIn
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class SignInUserMapper @Inject constructor() : UiMapper<FirebaseUser, User> {
    override fun mapToView(input: FirebaseUser): User {
        return User(
            uid = input.uid
        )
    }
}