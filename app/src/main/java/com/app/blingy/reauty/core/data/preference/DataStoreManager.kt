package com.app.blingy.reauty.core.data.preference

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = PreferencesConstant.PREFERENCES_NAME
)

@Singleton
class DataStoreManager @Inject constructor(@ApplicationContext context: Context) : AppPreferences {

    private val dataStore: DataStore<Preferences> = context.dataStore

    //preference keys
    private object PreferencesKeys {
        val KEY_IS_FIRST_TIME = booleanPreferencesKey(PreferencesConstant.KEY_IS_FIRST_TIME)
        val KEY_EMAIL_ADDRESS = stringPreferencesKey(PreferencesConstant.KEY_EMAIL_ADDRESS)
        val KEY_CREATE_PROFILE = booleanPreferencesKey(PreferencesConstant.KEY_CREATE_PROFILE)
        val KEY_UPDATE_UNIQUE_NAME =
            booleanPreferencesKey(PreferencesConstant.KEY_UPDATE_UNIQUE_NAME)
    }

    override suspend fun clearDataStore() {
        dataStore.edit {
            it.clear()
        }
    }

    override suspend fun setFirstTime(value: Boolean) {
        Timber.d("setFirstTime: called")
        dataStore.edit { preferences ->
            Timber.d("setFirstTime: value $value")
            // set first time user open the app in value
            preferences[PreferencesKeys.KEY_IS_FIRST_TIME] = value
            Timber.d("setFirstTime, ${preferences[PreferencesKeys.KEY_IS_FIRST_TIME]}")
        }
    }

    override fun getFirstTime(): Flow<Boolean> {
        Timber.d("getFirstTime: called")
        return dataStore.data.catch { exception ->
            // dataStore.data throws an IOException when an error is encountered when reading data
            if (exception is IOException) {
                Timber.d("getFirstTime: exception throw $exception")
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            // get first time the user opened the app in value, defaulting to false if not set:
            val value = preferences[PreferencesKeys.KEY_IS_FIRST_TIME] ?: false
            Timber.d("getFirstTime: value ${preferences[PreferencesKeys.KEY_IS_FIRST_TIME]}")
            value
        }
    }

    override suspend fun setEmailAddress(email: String) {
        Timber.d("setEmailAddress, called")
        dataStore.edit { preferences ->
            Timber.d("setEmailAddress, value: $email")
            // set email address of the user
            preferences[PreferencesKeys.KEY_EMAIL_ADDRESS] = email
            Timber.d("setEmailAddress, ${preferences[PreferencesKeys.KEY_EMAIL_ADDRESS]}")
        }
    }

    override fun getEmailAddress(): Flow<String> {
        Timber.d("getEmailAddress, called")
        return dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            val value = preferences[PreferencesKeys.KEY_EMAIL_ADDRESS] ?: ""
            Timber.d("getEmailAddress, ${preferences[PreferencesKeys.KEY_EMAIL_ADDRESS]}")
            value
        }
    }

    override suspend fun setCreateProfile(value: Boolean) {
        Timber.d("setCreateProfile, called")
        dataStore.edit { preferences ->
            Timber.d("setCreateProfile: value $value")
            // set user successfully created profile
            preferences[PreferencesKeys.KEY_CREATE_PROFILE] = value
            Timber.d("setCreateProfile, ${preferences[PreferencesKeys.KEY_CREATE_PROFILE]}")
        }
    }

    override fun hasCreateProfile(): Flow<Boolean> {
        Timber.d("hasCreateProfile: called")
        return dataStore.data.catch { exception ->
            // dataStore.data throws an IOException when an error is encountered when reading data
            if (exception is IOException) {
                Timber.d("hasCreateProfile: exception throw $exception")
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            // get first time the user created the profile for the app in value, defaulting to false if not set:
            val value = preferences[PreferencesKeys.KEY_CREATE_PROFILE] ?: false
            Timber.d("hasCreateProfile: value ${preferences[PreferencesKeys.KEY_CREATE_PROFILE]}")
            value
        }
    }

    override suspend fun setUniqueName(value: Boolean) {
        Timber.d("setUniqueName, called")
        dataStore.edit { preferences ->
            Timber.d("setUniqueName: value $value")
            // set user successfully created unique name
            preferences[PreferencesKeys.KEY_UPDATE_UNIQUE_NAME] = value
            Timber.d("setUniqueName, ${preferences[PreferencesKeys.KEY_UPDATE_UNIQUE_NAME]}")
        }
    }

    override fun hasUniqueName(): Flow<Boolean> {
        Timber.d("hasUniqueName: called")
        return dataStore.data.catch { exception ->
            // dataStore.data throws an IOException when an error is encountered when reading data
            if (exception is IOException) {
                Timber.d("hasUniqueName: exception throw $exception")
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            // get first time the user created the unique name for the app in value, defaulting to false if not set:
            val value = preferences[PreferencesKeys.KEY_UPDATE_UNIQUE_NAME] ?: false
            Timber.d("hasUniqueName: value ${preferences[PreferencesKeys.KEY_UPDATE_UNIQUE_NAME]}")
            value
        }
    }

}