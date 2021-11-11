package com.app.blingy.reauty.core.data.remote.service

import com.app.blingy.reauty.core.data.remote.constant.RemoteConstant.posts
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class PostService @Inject constructor(
    private val auth : FirebaseAuth,
    private val database : FirebaseDatabase
) {

    private fun getCurrentUserId() = auth.currentUser?.uid


     fun getPosts() = getCurrentUserId()?.let { database.getReference(posts).child(it).get() }


}