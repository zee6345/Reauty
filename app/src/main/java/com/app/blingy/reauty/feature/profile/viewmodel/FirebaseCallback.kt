package com.app.blingy.reauty.feature.profile.viewmodel

import com.app.blingy.reauty.feature.profile.viewstate.Response

interface FirebaseCallback {
    fun onResponse(response: Response)
}