package io.github.hkusu.architecturesampleapp.data.db

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class MainPreferencesDao @Inject constructor(@ApplicationContext private val context: Context) {

    private val Context.dataStore by preferencesDataStore("main")

    private val _age = intPreferencesKey("age")

    val ageFlow: Flow<Int> = context.dataStore.data
        .map { preferences ->
            preferences[_age] ?: 0
        }

    suspend fun setAge(age: Int) {
        context.dataStore.edit { preferences ->
            preferences[_age] = age
        }
    }
}
