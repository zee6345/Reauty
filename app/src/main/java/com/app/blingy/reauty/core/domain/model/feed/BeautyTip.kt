package com.app.blingy.reauty.core.domain.model.feed

/**
 * model to hold the data of Beauty Tip
 * @see [https://console.firebase.google.com/u/1/project/findme-57cf5/database/findme-57cf5/data/Beauty_tips](Beauty Tips Node)
 */
data class BeautyTip(
    val title: BeautyTipType = BeautyTipType.ACNE_PRONE,
    val description: String = "",
    val imageUrl: Int = 0,
)

enum class BeautyTipType(val value: String) {
    ACNE_PRONE("Acne Prone"),
    ANTI_AGING("Anti Aging"),
    CLEAN_BEAUTY("Clean Beauty"),
    CRUELTY_FREE("Cruelty Free"),
    DARK_CIRCLES("Dark Circles\\Puffiness"),
    DEHYDRATED("Dehydrated"),
    DULL_SKIN("Dull Skin"),
    LARGE_PORES("Large Pores\\Blackheads"),
    MENS_SKIN("Mens Skin"),
    NATURAL_BEAUTY("Natural Beauty"),
    REDNESS("Redness\\Blemishes"),
    VEGAN_BEAUTY("Vegan Beauty"),
}

