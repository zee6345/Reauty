package com.app.blingy.reauty

import android.app.Application
import com.app.blingy.reauty.core.util.Logger
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class ReautyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initLogger()
    }

    private fun initLogger() {
        Logger.init()
    }

}