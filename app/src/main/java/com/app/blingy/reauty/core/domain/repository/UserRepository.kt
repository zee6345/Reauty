package com.app.blingy.reauty.core.domain.repository


import com.app.blingy.reauty.core.data.repository.UserRepositoryImpl
import androidx.lifecycle.LiveData
import com.app.blingy.reauty.core.domain.model.user.User
import com.app.blingy.reauty.core.util.ResultOf
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.storage.StorageReference

import kotlinx.coroutines.flow.Flow

/**
 * repository for user operations
 * @see [UserRepositoryImpl]
 */
interface UserRepository {

    /**
     * to create a new user at users node
     * @param [user]
     * @return [ResultOf] of [Boolean]
     */
    fun createNewUser(user: User): ResultOf<Boolean>

    /**
     * to update a child user node with specific uid
     * data means, userName, gender and age
     * @param [user]
     * @return [ResultOf] of [Boolean]
     */
    fun updateUserData(user: User): ResultOf<Boolean>

    /**
     * to update a child user node with specific uid
     * data means, userSearchName
     * @param [user]
     * @return [ResultOf] of [Boolean]
     */
    fun updateUniqueName(user: User): ResultOf<Boolean>


    /**
     * to check whether or not the user's uniqueName is already take or not
     * @return [Task] of [DataSnapshot]
     */
    fun checkUniqueName(user: User): DatabaseReference

    fun getAllUsers(): DatabaseReference

    suspend fun getUserProfile(): Flow<Result<User>>

    fun uploadUserProfilePicture() :StorageReference



//    fun getUser(user: User) :ResultOf<User>

}