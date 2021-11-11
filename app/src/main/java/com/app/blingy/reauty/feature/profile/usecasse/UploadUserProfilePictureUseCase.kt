package com.app.blingy.reauty.feature.profile.usecasse

import com.app.blingy.reauty.core.domain.repository.UserRepository
import javax.inject.Inject

class UploadUserProfilePictureUseCase @Inject constructor(
    private val repo : UserRepository
) {
    operator fun invoke() = repo.uploadUserProfilePicture()
}