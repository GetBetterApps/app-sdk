package com.velkonost.getbetter.shared.features.addarea.presentation

import AreasRepository
import com.velkonost.getbetter.shared.core.model.area.TermsOfMembership
import com.velkonost.getbetter.shared.core.model.hint.UIHint
import com.velkonost.getbetter.shared.core.util.PagingConfig
import com.velkonost.getbetter.shared.core.util.isLoading
import com.velkonost.getbetter.shared.core.util.onSuccess
import com.velkonost.getbetter.shared.core.vm.BaseViewModel
import com.velkonost.getbetter.shared.features.addarea.api.AddAreaRepository
import com.velkonost.getbetter.shared.features.addarea.presentation.contract.AddAreaAction
import com.velkonost.getbetter.shared.features.addarea.presentation.contract.AddAreaClick
import com.velkonost.getbetter.shared.features.addarea.presentation.contract.AddAreaNavigation
import com.velkonost.getbetter.shared.features.addarea.presentation.contract.AddAreaViewState
import com.velkonost.getbetter.shared.features.addarea.presentation.contract.AreaChanged
import com.velkonost.getbetter.shared.features.addarea.presentation.contract.LoadNextPage
import com.velkonost.getbetter.shared.features.addarea.presentation.contract.NavigateBack
import com.velkonost.getbetter.shared.features.addarea.presentation.model.toUI
import com.velkonost.getbetter.shared.features.subscription.domain.CheckSubscriptionUseCase

class AddAreaViewModel
internal constructor(
    private val areasRepository: AreasRepository,
    private val addAreaRepository: AddAreaRepository,
    private val checkSubscriptionUseCase: CheckSubscriptionUseCase
) : BaseViewModel<AddAreaViewState, AddAreaAction, AddAreaNavigation, Nothing>(
    initialState = AddAreaViewState()
) {

    private val _areasPagingConfig = PagingConfig()

    init {
        fetchAreas()
        checkSubscription()
        showHint(firstTime = true)
    }

    override fun dispatch(action: AddAreaAction) = when (action) {
        is LoadNextPage -> fetchAreas()
        is AddAreaClick -> obtainAddAreaClick(action.areaId)
        is AreaChanged -> obtainAreaChanged(action.areaId)
        is NavigateBack -> emit(action)
        is AddAreaAction.HintClick -> showHint()
    }

    private fun checkSubscription() {
        launchJob {
            checkSubscriptionUseCase() collectAndProcess {
                onSuccess { result ->
                    result?.let {
                        emit(viewState.value.copy(showAds = !it.isActive))
                    }
                }
            }
        }
    }

    private fun showHint(firstTime: Boolean = false) {
        if (firstTime) {
            launchJob {
                if (addAreaRepository.shouldShowHint()) {
                    UIHint.DiaryAddArea.send()
                }
            }
        } else UIHint.DiaryAddArea.send()
    }

    private fun fetchAreas() {
        if (_areasPagingConfig.lastPageReached) return

        launchJob {
            areasRepository.fetchPublicAreasToAdd(
                page = _areasPagingConfig.page,
                perPage = _areasPagingConfig.pageSize,
            ) collectAndProcess {
                isLoading {
                    emit(viewState.value.copy(isLoading = it))
                }

                onSuccess { items ->
                    _areasPagingConfig.lastPageReached = items.isNullOrEmpty()
                    _areasPagingConfig.page++

                    items?.let {
                        val uiItems = viewState.value.items.plus(items.map { it.toUI() })
                        emit(viewState.value.copy(items = uiItems))
                    }
                }
            }
        }
    }

    private fun obtainAddAreaClick(areaId: Int) {
        launchJob {
            areasRepository.addUserArea(areaId) collectAndProcess {
                isLoading {
                    val items = viewState.value.items.toMutableList()
                    val areaIndex = items.indexOfFirst { area -> area.id == areaId }
                    items[areaIndex] = items[areaIndex].copy(isLoading = it)

                    emit(viewState.value.copy(items = items))
                }

                onSuccess {
                    val items = viewState.value.items.toMutableList()
                    val areaIndex = items.indexOfFirst { area -> area.id == areaId }
                    items[areaIndex] = items[areaIndex].copy(
                        termsOfMembership = TermsOfMembership.AlreadyJoined
                    )

                    emit(viewState.value.copy(items = items))
                }
            }
        }
    }

    private fun obtainAreaChanged(areaId: Int) {
        launchJob {
            areasRepository.fetchAreaDetails(areaId) collectAndProcess {
                isLoading {
                    val items = viewState.value.items.toMutableList()
                    val areaIndex = items.indexOfFirst { area -> area.id == areaId }
                    items[areaIndex] = items[areaIndex].copy(isLoading = it)

                    emit(viewState.value.copy(items = items))
                }
                onSuccess { updatedArea ->
                    updatedArea?.let {
                        val items = viewState.value.items.toMutableList()
                        val areaIndex = items.indexOfFirst { area -> area.id == updatedArea.id }

                        if (!updatedArea.isActive) {
                            items.removeAt(areaIndex)
                        } else {
                            items[areaIndex] = it.toUI()
                        }

                        emit(viewState.value.copy(items = items))
                    }
                }
            }
        }
    }
}