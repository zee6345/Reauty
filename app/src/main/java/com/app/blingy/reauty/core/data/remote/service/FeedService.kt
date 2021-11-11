package com.app.blingy.reauty.core.data.remote.service

import com.app.blingy.reauty.core.data.remote.constant.RemoteConstant
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

/**
 * every service that related to Feed Node
 */
@ExperimentalCoroutinesApi
class FeedService @Inject constructor(
    private val database: FirebaseDatabase
) {

    /**
     * to get all the feed from realtime database
     * @return [Task<DataSnapshot>!]
     */
    fun getFeeds(): Task<DataSnapshot> =
        database.getReference(RemoteConstant.feedsRef).get()



}