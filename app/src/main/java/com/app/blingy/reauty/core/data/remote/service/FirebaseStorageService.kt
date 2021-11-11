package com.app.blingy.reauty.core.data.remote.service

import com.app.blingy.reauty.core.data.remote.constant.RemoteConstant
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import javax.inject.Inject


class FirebaseStorageService @Inject constructor(
  private val firebaseStorage : FirebaseStorage,
  private val auth : FirebaseAuth

) {
    private val currentUserId = auth.currentUser?.uid

    fun getStorageRef()  = firebaseStorage.reference

    fun userProfilePicStorageRef () = getStorageRef().child(RemoteConstant.userProfilePicRef)



}