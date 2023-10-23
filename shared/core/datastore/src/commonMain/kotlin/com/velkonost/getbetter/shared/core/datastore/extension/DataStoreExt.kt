package com.velkonost.getbetter.shared.core.datastore.extension

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.velkonost.getbetter.shared.core.datastore.TOKEN_KEY
import kotlinx.coroutines.flow.first

suspend fun DataStore<Preferences>.getUserToken(): String? = data.first()[TOKEN_KEY]