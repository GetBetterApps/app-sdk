package com.velkonost.getbetter.shared.features.social

import com.velkonost.getbetter.shared.core.util.PagingConfig
import com.velkonost.getbetter.shared.core.vm.BaseViewModel
import com.velkonost.getbetter.shared.features.social.api.SocialRepository
import com.velkonost.getbetter.shared.features.social.contracts.SocialAction
import com.velkonost.getbetter.shared.features.social.contracts.SocialNavigation
import com.velkonost.getbetter.shared.features.social.contracts.SocialViewState
import kotlinx.coroutines.Job

class SocialViewModel
internal constructor(
    private val socialRepository: SocialRepository
) : BaseViewModel<SocialViewState, SocialAction, SocialNavigation, Nothing>(
    initialState = SocialViewState()
) {

    private val _generalFeedPagingConfig = PagingConfig()
    private val _areasFeedPagingConfig = PagingConfig()

    private var generalFeedLoadingJob: Job? = null
    private var areasFeedLoadingJob: Job? = null

    override fun dispatch(action: SocialAction) = when (action) {

        else -> {}
    }

    private fun fetchGeneralFeed() {
        if (_generalFeedPagingConfig.lastPageReached || generalFeedLoadingJob?.isActive == true) return

        generalFeedLoadingJob?.cancel()
        generalFeedLoadingJob = launchJob {
            socialRepository.fetchGeneralFeed(
                page = _generalFeedPagingConfig.page,
                pageSize = _generalFeedPagingConfig.pageSize
            ).collect { result ->
                with(result) {

                }
            }
        }
    }

    private fun fetchAreasFeed() {
        if (_areasFeedPagingConfig.lastPageReached || areasFeedLoadingJob?.isActive == true) return

        areasFeedLoadingJob?.cancel()
    }

}