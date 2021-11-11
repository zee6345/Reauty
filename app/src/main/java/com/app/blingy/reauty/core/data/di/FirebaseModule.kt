package com.app.blingy.reauty.core.data.di

import android.content.Context
import com.app.blingy.reauty.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.ActionCodeSettings
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.actionCodeSettings
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object FirebaseModule {

    /**
     * to provide GoogleSignInClient
     * @return [GoogleSignInClient]
     */
    @Provides
    @Singleton
    fun provideGoogleSignInClient(
        @ApplicationContext context: Context
    ): GoogleSignInClient {
        // to get the profile of the google account user
        val options = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(R.string.webclient_id))
            .requestEmail()
            .build()
        return GoogleSignIn.getClient(context, options)
    }

    /**
     * to provide Firebase auth singleton instance
     * @return [FirebaseAuth.getInstance]
     */
    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth =
        FirebaseAuth.getInstance()

    /**
     * to provide Firebase realtime database singleton instance
     * @return [FirebaseDatabase.getInstance]
     */
    @Provides
    @Singleton
    fun provideRealtimeDatabase(): FirebaseDatabase =
        FirebaseDatabase.getInstance()


    @Provides
    @Singleton
    fun providesFirebaseStorage() : FirebaseStorage =
        FirebaseStorage.getInstance()
}