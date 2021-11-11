package com.app.blingy.reauty.core.data.remote.service

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.ActionCodeSettings
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

/**
 * every service that related to Firebase Authentication
 */
class AuthService @Inject constructor(
    private val auth: FirebaseAuth
) {

    /**
     * to register the user to firebase auth with credential
     * @param [credential]
     * @return [Task<AuthResult>]
     */
    fun signInWithCredential(credential: AuthCredential): Task<AuthResult> =
        auth.signInWithCredential(credential)

    /**
     * to register the user to firebase auth with email and password
     * @param [email]
     * @param [password]
     * @return [Task<AuthResult>]
     */
    fun signInWithEmail(email: String, password: String): Task<AuthResult> =
        auth.signInWithEmailAndPassword(email, password)

    /**
     * to send email to user
     * @param [email]
     * @param [actionCodeSettings]
     * @see [ActionCodeSettings]
     */
    fun sendSignInLinkToEmail(email: String, actionCodeSettings: ActionCodeSettings) =
        auth.sendSignInLinkToEmail(email, actionCodeSettings)

    /**
     * to check is user is sign in with email link or not when the user return to the app
     * from email app or browser
     * @param [emailLink]
     * @return [Boolean]
     */
    fun isSignInWithEmailLink(emailLink: String) = auth.isSignInWithEmailLink(emailLink)

    /**
     * to sign in the user with their email and email link that send from firebase
     * @param [email]
     * @param [emailLink]
     * @return [Task<AuthResult>]
     */
    fun signInWithEmailLink(email: String, emailLink: String) =
        auth.signInWithEmailLink(email, emailLink)

}