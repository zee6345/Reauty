package com.app.blingy.reauty.core.data.di

import android.content.Context
import com.app.blingy.reauty.core.util.connectivity.ConnectionLiveData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideConnectivityManager(@ApplicationContext context: Context): ConnectionLiveData {
        return ConnectionLiveData(context)
    }

}