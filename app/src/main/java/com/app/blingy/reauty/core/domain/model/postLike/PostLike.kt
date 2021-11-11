package com.app.blingy.reauty.core.domain.model.postLike

/**
 * model to hold the data of Post Likes
 * @see [https://console.firebase.google.com/u/1/project/findme-57cf5/database/findme-57cf5/data/PostLikes](PostLikes Node)
 */
data class PostLike(
    val feedId: String = "",

)

/**
 * value class for post likes
 */
data class LikeUser(
    val userId: String = "",
    val isLike: Boolean = false
)