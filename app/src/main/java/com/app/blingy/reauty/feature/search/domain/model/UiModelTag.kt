package com.app.blingy.reauty.feature.search.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UiModelTag(
    val imageUrl: String = "",
    val postCount: Int = 0,
): Parcelable