package com.velkonost.getbetter.shared.features.subscription.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.velkonost.getbetter.shared.features.subscription.api.SubscriptionRepository

class SubscriptionRepositoryImpl(
    private val localDataSource: DataStore<Preferences>
) : SubscriptionRepository