package com.app.blingy.reauty.core.domain.model.introChat

/**
 * data holder for chat message for one time intro left event
 */
data class ChatIntroLeft(
    val text: String = "",
    val timeStamp: String = ""
)

/**
 * data holder for chat message for one time intro right event
 */
data class ChatIntroRight(
    val text: String = "",
    val image: Int = 0,
    val timeStamp: String = ""
)