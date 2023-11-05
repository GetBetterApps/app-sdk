package com.velkonost.getbetter.shared.features.social.contracts

import com.velkonost.getbetter.shared.core.model.note.Note
import com.velkonost.getbetter.shared.core.util.PrefetchDistanceValue
import com.velkonost.getbetter.shared.core.vm.contracts.UIContract
import com.velkonost.getbetter.shared.features.social.model.SocialTab

data class SocialViewState(
    val tabs: List<SocialTab> = SocialTab.values().toList(),
    val generalFeed: FeedViewState = FeedViewState(),
    val areasFeed: FeedViewState = FeedViewState()
) : UIContract.State

data class FeedViewState(
    val isLoading: Boolean = true,
    val items: List<Note> = emptyList(),
    val loadMorePrefetch: Int = PrefetchDistanceValue
) : UIContract.State
