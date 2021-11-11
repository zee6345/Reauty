package com.app.blingy.reauty.feature.home.domain.usecase

import com.app.blingy.reauty.R
import com.app.blingy.reauty.core.domain.model.feed.BeautyTip
import com.app.blingy.reauty.core.domain.model.feed.BeautyTipType
import javax.inject.Inject

class GetBeautyTipsUseCase @Inject constructor() {

    operator fun invoke(): List<BeautyTip> {
        return listOf(
            BeautyTip(
                title = BeautyTipType.ACNE_PRONE,
                description = "Acne Prone",
                imageUrl = R.drawable.ic_acne_prone
            ),
            BeautyTip(
                title = BeautyTipType.ANTI_AGING,
                description = "Anti Aging",
                imageUrl = R.drawable.ic_anti_aging
            ),
            BeautyTip(
                title = BeautyTipType.CLEAN_BEAUTY,
                description = "Clean Beauty",
                imageUrl = R.drawable.ic_clean_beauty
            ),
            BeautyTip(
                title = BeautyTipType.CRUELTY_FREE,
                description = "Cruelty Free",
                imageUrl = R.drawable.ic_cruelty_free
            ),
            BeautyTip(
                title = BeautyTipType.DARK_CIRCLES,
                description = "Dark Circles Puffiness",
                imageUrl = R.drawable.ic_dark_circles_puffiness
            ),
            BeautyTip(
                title = BeautyTipType.DEHYDRATED,
                description = "Dehydrated",
                imageUrl = R.drawable.ic_dehydrated
            ),
            BeautyTip(
                title = BeautyTipType.DULL_SKIN,
                description = "Dull Skin",
                imageUrl = R.drawable.ic_dull_skin
            ),
            BeautyTip(
                title = BeautyTipType.LARGE_PORES,
                description = "Large Pores Blackheads",
                imageUrl = R.drawable.ic_large_pores_blackheads
            ),
            BeautyTip(
                title = BeautyTipType.MENS_SKIN,
                description = "Mens Skin",
                imageUrl = R.drawable.ic_mens_skin
            ),
            BeautyTip(
                title = BeautyTipType.NATURAL_BEAUTY,
                description = "Natural Beauty",
                imageUrl = R.drawable.ic_natural_beauty
            ),
            BeautyTip(
                title = BeautyTipType.REDNESS,
                description = "Redness Blemishes",
                imageUrl = R.drawable.ic_redness_blemishes
            ),
            BeautyTip(
                title = BeautyTipType.VEGAN_BEAUTY,
                description = "Vegan Beauty",
                imageUrl = R.drawable.ic_vegan_beauty
            ),
        )
    }

}