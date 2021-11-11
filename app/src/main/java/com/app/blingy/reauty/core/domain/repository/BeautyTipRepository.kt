package com.app.blingy.reauty.core.domain.repository

import com.app.blingy.reauty.core.data.repository.BeautyTipRepositoryImpl
import com.app.blingy.reauty.core.domain.model.feed.BeautyTip
import kotlinx.coroutines.flow.Flow

/**
 * repository for Firebase Realtime Database Beauty Tips DocumentReference operations
 * @see [BeautyTipRepository]
 */
interface BeautyTipRepository {

    /**
     * to get the Beauty Tips from realtime database
     * @see [Flow] [Result]
     * @return list of [BeautyTip] or null
     */
   // fun getBeautyTips(): Flow<BeautyTip>

}