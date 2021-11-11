package com.app.blingy.reauty.core.domain.repository

import com.app.blingy.reauty.core.data.repository.AuthRepositoryImpl
import com.app.blingy.reauty.core.util.ResultOf
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseUser

/**
 * repository for Firebase operations
 * @see [AuthRepositoryImpl]
 */
interface AuthRepository {

    /**
     * to sign in to firebase with credential
     * @return [GoogleSignInClient]
     */
    fun getGoogleSignInIntent(): GoogleSignInClient

    /**
     * to sign in to firebase with credential
     * @param [credential]
     * @return [FirebaseUser] or null
     */
    suspend fun signInWithCredential(credential: AuthCredential): FirebaseUser?

    /**
     * to sign in to firebase auth
     * @param [email] email of the user
     * @param [password] password of the user
     * @return [FirebaseUser] or null
     */
    suspend fun signInWithEmail(email: String, password: String): FirebaseUser?

    /**
     * to sign in to firebase auth without password
     * @param [email] email of the user
     */
    suspend fun sendSignInLinkToEmail(email: String): ResultOf<Boolean>

    /**
     * to sign in to firebase authentication with email and emailLink
     * @param [email]
     * @param [emailLink]
     * @return [ResultOf] of [FirebaseUser]
     */
    suspend fun signInWithEmailLink(email: String, emailLink: String): ResultOf<FirebaseUser>

    /**
     * to check whether or not user is sign in with email link that send from firebase or not
     * @return [Boolean]
     */
    fun isSignInWithEmailLink(emailLink: String): Boolean

}