package com.example.quoteplus.data.local

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.quoteplus.QuotesApp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Singleton


object DataStoreManager {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "quotes")
    private val TOKEN = stringPreferencesKey("TOKEN")

    // Method to save token to DataStore
    suspend fun saveTokenToDataStore(context: Context, token: String) {
        context.dataStore.edit { preferences ->
            preferences[TOKEN] = token
        }
    }

    // Flow to get the token from DataStore
    fun getToken(context: Context): Flow<String> {
        return context.dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    Log.e("DataStoreManager", "Error reading token: ${exception.message}")
                    throw exception
                }
            }
            .map { preferences ->
                preferences[TOKEN] ?: ""
            }
    }
}