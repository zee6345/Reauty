package com.app.blingy.reauty.core.domain.model.postComment

data class Comment(
    val comment: String = "",
    val commentOwnerID: String = "",
    val commentOwnerImage: String = "",
    val commentOwnerName: String = "",
    val postOwnerID: String = "",
    val timeStamp: Long = 0L
)
