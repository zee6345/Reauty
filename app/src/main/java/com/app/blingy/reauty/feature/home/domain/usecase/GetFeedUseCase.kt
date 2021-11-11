package com.app.blingy.reauty.feature.home.domain.usecase

import com.app.blingy.reauty.core.domain.repository.FeedRepository
import javax.inject.Inject

class GetFeedUseCase @Inject constructor(
    private val repo: FeedRepository
) {
    suspend operator fun invoke() = repo.getFeeds()
}