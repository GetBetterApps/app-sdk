package com.velkonost.getbetter.shared.features.social.contracts

import com.velkonost.getbetter.UtilBuildKonfig
import com.velkonost.getbetter.shared.core.model.note.Note
import com.velkonost.getbetter.shared.core.util.PrefetchDistanceValue
import com.velkonost.getbetter.shared.core.vm.contracts.UIContract
import com.velkonost.getbetter.shared.features.social.model.SocialTab

data class SocialViewState(
    val tabs: List<SocialTab> = SocialTab.entries,
    val generalFeed: FeedViewState = FeedViewState(),
    val areasFeed: FeedViewState = FeedViewState(),
    val adPosition: Int = (6..10).random(),
    val adId: String = UtilBuildKonfig.AD_ID//UtilBuildKonfig.RUSTORE_AD_ID
) : UIContract.State

data class FeedViewState(
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val items: List<Note> = emptyList(),
    val loadMorePrefetch: Int = PrefetchDistanceValue
) : UIContract.State
