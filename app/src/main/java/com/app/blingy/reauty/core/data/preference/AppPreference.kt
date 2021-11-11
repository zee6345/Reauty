package com.app.blingy.reauty.core.data.preference

import kotlinx.coroutines.flow.Flow

/**
 * contract for preferences
 * @see [DataStoreManager]]
 */
interface AppPreferences {

    /**
     * to clear all data
     */
    suspend fun clearDataStore()

    /**
     * to set the first time user used the app
     */
    suspend fun setFirstTime(value: Boolean)

    /**
     * to get is user is first time or not
     */
    fun getFirstTime(): Flow<Boolean>

    /**
     * to used user email when user pressed login in link from their email
     */
    suspend fun setEmailAddress(email: String)

    /**
     * to store the user email before user leave their email app or browser
     */
    fun getEmailAddress(): Flow<String>

    /**
     * to set user is successfully created profile
     */
    suspend fun setCreateProfile(value: Boolean)

    /**
     * to check user profile was created or not
     */
    fun hasCreateProfile(): Flow<Boolean>

    /**
     * to set user is successfully create unique name (search name)
     */
    suspend fun setUniqueName(value: Boolean)

    /**
     * to check user have unique name or not
     */
    fun hasUniqueName(): Flow<Boolean>

}