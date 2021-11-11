package com.app.blingy.reauty.core.data.di

import com.app.blingy.reauty.core.data.repository.*
import com.app.blingy.reauty.core.domain.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    /**
     * to get the Authentication Repository
     * @see [AuthRepository]
     * @see [AuthRepositoryImpl]
     */
    @Binds
    abstract fun bindAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    /**
     * to get the Beauty Tips Repository
     * @see [BeautyTipRepository]
     * @see [BeautyTipRepositoryImpl]
     */
    @Binds
    abstract fun bindBeautyTipRepository(beautyTipRepositoryImpl: BeautyTipRepositoryImpl): BeautyTipRepository

    /**
     * to get the Feed Repository
     * @see [FeedRepository]
     * @see [FeedRepositoryImpl]
     */
    @Binds
    abstract fun bindFeedRepository(feedRepositoryImpl: FeedRepositoryImpl): FeedRepository

    /**
     * to get the User Repository
     * @see [UserRepository]
     * @see [UserRepositoryImpl]
     */
    @Binds
    abstract fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository


    @Binds
    abstract fun bindPostRepository(postRepositoryImpl: PostRepositoryImpl) : PostRepository

}