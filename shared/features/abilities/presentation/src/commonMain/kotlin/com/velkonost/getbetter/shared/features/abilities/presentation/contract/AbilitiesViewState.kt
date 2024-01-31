package com.velkonost.getbetter.shared.features.abilities.presentation.contract

import com.velkonost.getbetter.shared.core.model.task.Ability
import com.velkonost.getbetter.shared.core.util.PrefetchDistanceValue
import com.velkonost.getbetter.shared.core.util.advertise.ACTUAL_AD_ID
import com.velkonost.getbetter.shared.core.vm.contracts.UIContract
import com.velkonost.getbetter.shared.features.abilities.presentation.model.FavoriteAbility

data class AbilitiesViewState(
    val isLoading: Boolean = false,
    val loadMorePrefetch: Int = PrefetchDistanceValue,
    val items: List<Ability> = listOf(FavoriteAbility),

    val adId: String = ACTUAL_AD_ID,
    val adPosition: Int = (6..10).random()
) : UIContract.State