package com.app.blingy.reauty.core.data.remote.service

import com.app.blingy.reauty.core.data.remote.constant.RemoteConstant
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import javax.inject.Inject

/**
 * every service that related to Beauty Tips Node
 */
class BeautyTipService @Inject constructor(
    private val database: FirebaseDatabase
) {

    /**
     * to get all the feed from realtime database
     * @return [Task<DataSnapshot>!]
     */
    fun getBeautyTips(): Task<DataSnapshot> =
        database.getReference(RemoteConstant.beautyTipsRef).get()

}