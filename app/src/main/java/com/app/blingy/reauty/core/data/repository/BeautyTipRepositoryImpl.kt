package com.app.blingy.reauty.core.data.repository

import com.app.blingy.reauty.core.data.remote.service.BeautyTipService
import com.app.blingy.reauty.core.domain.model.feed.BeautyTip
import com.app.blingy.reauty.core.domain.repository.BeautyTipRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

/**
 * repository for Beauty Tip Node
 * @see [BeautyTipRepository]
 * @see [https://console.firebase.google.com/u/1/project/findme-57cf5/database/findme-57cf5/data/Beauty_tips](Beauty Tips Node)
 */
class BeautyTipRepositoryImpl @Inject constructor(
    private val service: BeautyTipService
) : BeautyTipRepository {
//    override fun getBeautyTips(): Flow<BeautyTip> {
//        return
//    }
}