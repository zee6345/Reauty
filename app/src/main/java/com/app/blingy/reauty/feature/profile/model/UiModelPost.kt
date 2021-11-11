package com.app.blingy.reauty.feature.profile.model

import com.app.blingy.reauty.core.domain.model.post.Post

/**
 * model to hold the data of Post
 */

data class UiModelPost(
    val comments: String = "",
    val haveHashTagsMention: Boolean = false,
    val havePersonalMention: Boolean = false,
    val haveProductMention: Boolean = false,
    var likes: Int = 0,
    val postImageLink: String = "",
    val postOwnerID: String = "",
    val postOwnerName: String = "",
    val postText: String = "",
    val postType: String = "",
    val postVideoLink: String = "",
    val post_status: String = "",
    val timeStamp: Long = 0L,
    val userProfileImage: String  = "",
    var isExpand: Boolean = false,
    var isFavourite: Boolean = false
)

fun Post.toUiModelPost() : UiModelPost{
    return UiModelPost(
       comments = comments,
    haveHashTagsMention = haveHashTagsMention,
    havePersonalMention = havePersonalMention,
     haveProductMention = haveProductMention,
     likes = likes,
     postImageLink = postImageLink,
     postOwnerID = postOwnerID,
     postOwnerName = postOwnerName,
     postText = postText,
    postType = postType,
     postVideoLink = postVideoLink,
     post_status = post_status,
     timeStamp = timeStamp,
     userProfileImage = userProfileImage
    )
}
