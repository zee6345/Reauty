package com.app.blingy.reauty.core.domain.model.skinCare

import com.app.blingy.reauty.R

enum class SkinCare(val imageUrl: Int) {
    NON_COMEDOGENIC(R.drawable.ic_sc_non_comedogenic),
    SLES_FREE(R.drawable.ic_sc_sles_free),
    ANTI_AGING(R.drawable.ic_sc_anti_aging),
    OIL_FREE(R.drawable.ic_sc_oil_free),
    ALCOHOL_FREE(R.drawable.ic_sc_alcohol_free),
    CONTAINS_SPF(R.drawable.ic_sc_contains_spf),
    PARABEN_FREE(R.drawable.ic_sc_paraben_free),
    GLUTEN_FREE(R.drawable.ic_sc_gluten_free),
    TALC_FREE(R.drawable.ic_sc_talc_free),
    FRAGRANCE_FREE(R.drawable.ic_sc_fragrance_free),
}

val skinCareList = listOf<String>(
    "Non-Comedogenic",
    "SLES-Free",
    "Anti-Aging",
    "Oil-Free",
    "Alcohol-Free",
    "Contains SPF",
    "Parabens-Free",
    "Fragrance-Free",
    "Phthalate-Free",
    "Talc-Free",
    "Gluten-Free",
)
