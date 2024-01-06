package com.epitech.cashmanagerinterface.common.data.local

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

private val Context.dataStore by preferencesDataStore(
    name = "PreferenceDataStore"
)

class PreferenceDataStoreHelper(context: Context):IPreferenceDataStoreAPI {
    private val dataSource = context.dataStore

    override suspend fun <T> getPreference(key: Preferences.Key<T>, defaultValue: T):
            T = dataSource.data.first()[key] ?: defaultValue

    override suspend fun <T> putPreference(key: Preferences.Key<T>, value: T) {
        dataSource.edit { preferences ->
            preferences[key] = value
        }
    }

    override suspend fun <T> removePreference(key: Preferences.Key<T>) {
        dataSource.edit { preferences ->
            preferences.remove(key)
        }
    }
}