package com.app.blingy.reauty.feature.search.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UiModelAccount(
    val imageUrl: String = "",
    val userName: String = "",
    val reviewCount: Int = 0,
    val skinTypeImage: String = "",
    val skinCareImageFirst: String = "",
    val skinCareImageSecond: String = "",
    val skinCareImageThird: String = "",
) : Parcelable