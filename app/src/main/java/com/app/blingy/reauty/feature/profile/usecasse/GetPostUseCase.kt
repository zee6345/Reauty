package com.app.blingy.reauty.feature.profile.usecasse

import com.app.blingy.reauty.core.domain.repository.PostRepository
import javax.inject.Inject

class GetPostUseCase @Inject constructor(
    private val postRepository: PostRepository
) {
    suspend operator fun invoke() = postRepository.getPosts()
}