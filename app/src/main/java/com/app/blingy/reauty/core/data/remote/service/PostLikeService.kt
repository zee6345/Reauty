package com.app.blingy.reauty.core.data.remote.service

import com.app.blingy.reauty.core.data.remote.constant.RemoteConstant
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import javax.inject.Inject

/**
 * every service that related to Post Like Node
 */
class PostLikeService @Inject constructor(
    private val database: FirebaseDatabase
) {

    /**
     * to get all the post likes from realtime database
     * @return [DatabaseReference]
     */
    fun getPostLikes(): DatabaseReference =
        database.getReference(RemoteConstant.postLikesRef)

    /**
     * to get the post likes from realtime database by post id (feed id)
     * @return [Task<DataSnapshot>]
     */
    fun getPostLikesById(postId: String): Task<DataSnapshot> =
        database.getReference(RemoteConstant.postLikesRef)
            .child(postId)
            .get()

}