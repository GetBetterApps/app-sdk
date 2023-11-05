package com.velkonost.getbetter.shared.core.datastore.extension

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.velkonost.getbetter.shared.core.datastore.KEYS_TO_REFRESH
import com.velkonost.getbetter.shared.core.datastore.TOKEN_KEY
import kotlinx.coroutines.flow.first

suspend fun DataStore<Preferences>.getUserToken(): String? = data.first()[TOKEN_KEY]

suspend fun DataStore<Preferences>.clear() = this.edit { preferences ->
    KEYS_TO_REFRESH.forEach { key ->
        preferences.remove(key)
    }
}