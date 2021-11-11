package com.app.blingy.reauty.core.util

import android.text.TextUtils
import android.util.Patterns

object Utils {

    fun isValidEmail(target: CharSequence?): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target!!).matches()
    }
}