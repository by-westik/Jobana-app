package com.kfd.jobana.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferences(
    private val context: Context
) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("user_data_store")
        private val AUTH_KEY = stringPreferencesKey("auth_key")
    }

    val authToken: Flow<String?>
        get() =  context.dataStore.data.map { preferences ->
            preferences[AUTH_KEY]
        }


    suspend fun saveUserAuthToken(authToken: String) {
        context.dataStore.edit {preferences ->
            preferences[AUTH_KEY] = authToken

        }
    }


}