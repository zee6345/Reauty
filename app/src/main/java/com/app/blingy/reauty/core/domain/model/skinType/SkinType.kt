package com.app.blingy.reauty.core.domain.model.skinType

import com.app.blingy.reauty.R

enum class SkinType(val imageUrl: Int) {
    Combination(R.drawable.ic_sk_combination),
    Dry(R.drawable.ic_sk_dry),
    Normal(R.drawable.ic_sk_normal),
    Oily(R.drawable.ic_sk_oily),
    Mature(R.drawable.ic_sk_sensitive),
}

val skinTypeList = listOf<String>(
    "Combination",
    "Mature",
    "Oily",
    "Dry",
    "Normal",
)