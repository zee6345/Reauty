package com.app.blingy.reauty.core.data.repository

import com.app.blingy.reauty.BuildConfig
import com.app.blingy.reauty.core.data.remote.constant.RemoteConstant
import com.app.blingy.reauty.core.data.remote.service.AuthService
import com.app.blingy.reauty.core.domain.repository.AuthRepository
import com.app.blingy.reauty.core.util.ResultOf
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.actionCodeSettings
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

/**
 * repository patter of Firebase operations
 * @see [AuthRepository]
 */
class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService,
    private val googleSignInClient: GoogleSignInClient,
) : AuthRepository {

    override fun getGoogleSignInIntent(): GoogleSignInClient {
        return googleSignInClient
    }

    override suspend fun signInWithCredential(credential: AuthCredential): FirebaseUser? {
        val result = authService.signInWithCredential(credential).await()
        return result.user
    }

    override suspend fun signInWithEmail(email: String, password: String): FirebaseUser? {
        val result = authService.signInWithEmail(email, password).await()
        return result.user ?: throw FirebaseAuthException("", "")
    }

    override suspend fun sendSignInLinkToEmail(email: String): ResultOf<Boolean> {
        return try {
            val actionCodeSettings = actionCodeSettings {

                url = RemoteConstant.remoteUrl
                handleCodeInApp = true
                setAndroidPackageName(
                    BuildConfig.APPLICATION_ID,
                    false,
                    RemoteConstant.minSdk
                )
            }
            authService.sendSignInLinkToEmail(email, actionCodeSettings).await()
            ResultOf.Success(true)
        } catch (e: FirebaseAuthException) {
            ResultOf.Failure(e)
        }
    }

    override suspend fun signInWithEmailLink(
        email: String,
        emailLink: String
    ): ResultOf<FirebaseUser> {
        return try {
            val result = authService.signInWithEmailLink(email, emailLink).await()
            ResultOf.Success(result.user!!)
        } catch (e: FirebaseAuthException) {
            ResultOf.Failure(e)
        }
    }

    override fun isSignInWithEmailLink(emailLink: String): Boolean {
        return authService.isSignInWithEmailLink(emailLink)
    }


}