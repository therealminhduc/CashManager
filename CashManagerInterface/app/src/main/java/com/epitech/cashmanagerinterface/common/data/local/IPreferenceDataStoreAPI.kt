package com.epitech.cashmanagerinterface.common.data.local

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

interface IPreferenceDataStoreAPI {
    suspend fun <T> getPreference(key: Preferences.Key<T>, defaultValue: T):T
    suspend fun <T> putPreference(key: Preferences.Key<T>, value: T)
    suspend fun <T> removePreference(key: Preferences.Key<T>)
}