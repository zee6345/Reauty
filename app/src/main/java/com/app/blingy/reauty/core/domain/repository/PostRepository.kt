package com.app.blingy.reauty.core.domain.repository

import com.app.blingy.reauty.core.domain.model.post.Post
import kotlinx.coroutines.flow.Flow

interface PostRepository {

    suspend fun getPosts(): Flow<Result<List<Post>>>

}