package com.app.blingy.reauty.core.domain.model.message

data class MessagePreview(
    val isRead :Boolean = false,
    val profilePicLink:String = "",
    val text:String = "" ,
    val timeStamp: Long = 0L,
    val typeOfuser: String = "",
    val username: String = ""
)
