package com.app.blingy.reauty.core.data.remote.service

import com.app.blingy.reauty.core.data.remote.constant.ChildConstant
import com.app.blingy.reauty.core.data.remote.constant.RemoteConstant
import com.app.blingy.reauty.core.domain.model.user.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

import com.google.firebase.database.ServerValue

import com.google.firebase.database.Query

import javax.inject.Inject

/**
 * every service that related to Users Node
 */
class UserService @Inject constructor(
    private val auth: FirebaseAuth,
    private val database: FirebaseDatabase
) {

     fun getCurrentUserId() = auth.currentUser?.uid

    private fun updateCreateAtDateTime() {
        getCurrentUserId()?.let {
            database.getReference(RemoteConstant.testUserRef)
                .child(it)
                .child(ChildConstant.UserConstant.CREATED_AT)
                .setValue(ServerValue.TIMESTAMP)
        }
    }

    private fun updateModifiedAtDateTime() {
        getCurrentUserId()?.let {
            database.getReference(RemoteConstant.testUserRef)
                .child(it)
                .child(ChildConstant.UserConstant.MODIFIED_AT)
                .setValue(ServerValue.TIMESTAMP)
        }
    }

    /**
     * to create child node with their uid under users node
     * @param [user]
     */
    fun createUserProfile(user: User) {
        database.getReference(RemoteConstant.testUserRef)
            .child(user.uid)
            .setValue(user)
        updateCreateAtDateTime()
    }

    /**
     * to update the data of user with their specific uid
     * data means, userName, gender and age
     * @param [user]
     */
    fun updateUserData(user: User) {
        val updateData = mapOf<String, Any>(
            ChildConstant.UserConstant.USER_NAME to user.userName,
            ChildConstant.UserConstant.GENDER to user.gender,
            ChildConstant.UserConstant.AGE to user.age,
            ChildConstant.UserConstant.PROFILE_Pic_Link to user.profilePicLink,
            "bioText" to user.bioText,
            "firstName" to user.firstName,
        )
        getCurrentUserId()?.let { uid ->
            database.getReference(RemoteConstant.testUserRef)
                .child(uid)
                .updateChildren(updateData)
        }

        updateModifiedAtDateTime()


    }

    /**
     * to update the data of user with their specific uid
     * data means userSearchName
     * @param [user]
     */
    fun updateUniqueName(user: User) {
        getCurrentUserId()?.let { uid ->
            database.getReference(RemoteConstant.testUserRef)
                .child(uid)

                .child(ChildConstant.UserConstant.USER_SEARCH_NAME)
                .setValue(user.userSearchName)
        }
    }

    /**
     * to check whether or not the unique name (userSearchName) is already taken or not
     * @return [Task] of [DataSnapshot]
     */
    fun checkUniqueName(user: User): DatabaseReference {
        return database.getReference(RemoteConstant.testUserRef)
    }

    fun getAllUsers(): DatabaseReference {
        return database.getReference(RemoteConstant.usersRef)
    }

    fun getUserProfile() = getCurrentUserId()?.let { uid ->
//        database.getReference(RemoteConstant.testUserRef).child(uid)
//                .child("userSearchName")
//                .setValue(user.userSearchName)

    }
//    fun checkUniqueName(user: User): Query {
//        return database.getReference(RemoteConstant.testUserRef)
//            .orderByChild("userSearchName")
//            .equalTo(user.userSearchName)
//
//    }

}