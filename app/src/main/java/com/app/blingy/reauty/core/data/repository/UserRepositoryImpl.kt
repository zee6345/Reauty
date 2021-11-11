package com.app.blingy.reauty.core.data.repository


import com.app.blingy.reauty.core.data.remote.service.FirebaseStorageService

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData

import com.app.blingy.reauty.core.data.remote.service.UserService
import com.app.blingy.reauty.core.domain.model.feed.Feed
import com.app.blingy.reauty.core.domain.model.user.User
import com.app.blingy.reauty.core.domain.repository.UserRepository
import com.app.blingy.reauty.core.util.ResultOf
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseException
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener

import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.channels.awaitClose

import kotlinx.coroutines.flow.Flow

import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

/**
 * repository patter of user operations
 * @see [UserRepository]
 */
class UserRepositoryImpl @Inject constructor(
    private val userService: UserService,
    private val firebaseStorageService: FirebaseStorageService
) : UserRepository {

    override fun createNewUser(user: User): ResultOf<Boolean> {
        return try {
            userService.createUserProfile(user)
            ResultOf.Success(true)
        } catch (e: FirebaseException) {
            ResultOf.Failure(e)
        }
    }

    override fun updateUserData(user: User): ResultOf<Boolean> {
        return try {
            userService.updateUserData(user)
            ResultOf.Success(true)
        } catch (e: FirebaseException) {
            ResultOf.Failure(e)
        }
    }

    override fun updateUniqueName(user: User): ResultOf<Boolean> {
        return try {
            userService.updateUniqueName(user)
            ResultOf.Success(true)
        } catch (e: FirebaseException) {
            ResultOf.Failure(e)
        }
    }

    override fun checkUniqueName(user: User): DatabaseReference = userService.checkUniqueName(user)

    //   override fun checkUniqueName(user: User) = userService.checkUniqueName(user)

    override fun getAllUsers(): DatabaseReference {
        return userService.getAllUsers()
    }

    override fun uploadUserProfilePicture(): StorageReference {
        return try {
            firebaseStorageService.userProfilePicStorageRef()
                .child(userService.getCurrentUserId()!!)

        } catch (e: FirebaseException) {
            throw  e
        }
    }

    override suspend fun getUserProfile() = callbackFlow<Result<User>> {
        val postListener = object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                this@callbackFlow.trySend(Result.failure(error.toException())).isSuccess
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val items = dataSnapshot.getValue(User::class.java)

                this@callbackFlow.trySend(Result.success(items!!))
            }
        }
//        userService.getUserProfile()?.addValueEventListener(postListener)
//        awaitClose {
//            userService.getUserProfile()?.removeEventListener(postListener)
//        }
    }
//    override fun checkUniqueName(user: User): ResultOf<Boolean> {
//        return try {
//            userService.checkUniqueName(user)
//
//            ResultOf.Success(true)
//        }catch (e: FirebaseException){
//            ResultOf.Failure(e)
//        }
//    }
}




