package io.github.hkusu.architecturesampleapp.data.db

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import dagger.hilt.android.qualifiers.ApplicationContext
import io.github.hkusu.architecturesampleapp.data.di.mainDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MainPreferencesDao @Inject constructor(@ApplicationContext private val context: Context) {

    private val AGE = intPreferencesKey("age")

    val ageFlow: Flow<Int> = context.mainDataStore.data
        .map { preferences ->
            preferences[AGE] ?: 0
        }

    suspend fun setAge(age: Int) {
        context.mainDataStore.edit { preferences ->
            preferences[AGE] = age
        }
    }
}
