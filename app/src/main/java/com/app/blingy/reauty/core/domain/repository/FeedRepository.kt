package com.app.blingy.reauty.core.domain.repository

import com.app.blingy.reauty.core.data.repository.FeedRepositoryImpl
import com.app.blingy.reauty.core.domain.model.feed.Feed
import com.app.blingy.reauty.core.domain.model.user.User
import kotlinx.coroutines.flow.Flow

/**
 * repository for Firebase Realtime Database Feed DocumentReference operations
 * @see [FeedRepositoryImpl]
 */
interface FeedRepository {

    /**
     * to sign in to firebase with credential
     * * @see [Flow] [Result]
     * @return list of [Feed] or null
     */
    suspend fun getFeeds(): Flow<Result<List<Feed>>>

}