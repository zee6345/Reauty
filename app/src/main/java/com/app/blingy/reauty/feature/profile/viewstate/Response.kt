package com.app.blingy.reauty.feature.profile.viewstate

import com.app.blingy.reauty.core.domain.model.user.User
import com.app.blingy.reauty.core.presentation.Event
import com.app.blingy.reauty.feature.home.domain.model.UiModelBeautyTip

data class Response(
    var user: User? = null,
    var exception: Exception? = null
)