package com.app.blingy.reauty.core.domain.repository

import com.app.blingy.reauty.core.data.repository.FeedRepositoryImpl
import com.app.blingy.reauty.core.domain.model.feed.Feed
import kotlinx.coroutines.flow.Flow

/**
 * repository for Firebase Realtime Database Post Likes DocumentReference operations
 * @see [FeedRepositoryImpl]
 */
interface PostLikeRepository {

    /**
     * to sign in to firebase with credential
     * * @see [Flow] [Result]
     * @return list of [Feed] or null
     */
    suspend fun getPostLikes(): Flow<Result<List<Feed>>>

}