package com.app.blingy.reauty.core.data.di

import com.app.blingy.reauty.core.data.preference.DataStoreManager
import com.app.blingy.reauty.core.data.preference.AppPreferences
import com.app.blingy.reauty.core.util.dispatcher.CoroutineDispatcherProvider
import com.app.blingy.reauty.core.util.dispatcher.DispatcherProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataStoreModule {

    /**
     * to bind the DataStore(Preferences)
     * @see [AppPreferences]
     * @see [DataStoreManager]
     */
    @Binds
    abstract fun bindAppDataStore(dataStoreManager: DataStoreManager): AppPreferences

    /**
     * to get the desire dispatchers
     * @see [DispatcherProvider]
     * @see [CoroutineDispatcherProvider]
     */
    @Binds
    abstract fun bindDispatchersProvider(dispatchersProvider: CoroutineDispatcherProvider):
            DispatcherProvider

}