package com.velkonost.getbetter.shared.features.abilities.presentation

import com.velkonost.getbetter.shared.core.util.PagingConfig
import com.velkonost.getbetter.shared.core.util.isLoading
import com.velkonost.getbetter.shared.core.util.onSuccess
import com.velkonost.getbetter.shared.core.vm.BaseViewModel
import com.velkonost.getbetter.shared.features.abilities.api.AbilitiesRepository
import com.velkonost.getbetter.shared.features.abilities.presentation.contract.AbilitiesAction
import com.velkonost.getbetter.shared.features.abilities.presentation.contract.AbilitiesNavigation
import com.velkonost.getbetter.shared.features.abilities.presentation.contract.AbilitiesViewState
import kotlinx.coroutines.Job

class AbilitiesViewModel
internal constructor(
    private val abilitiesRepository: AbilitiesRepository
) : BaseViewModel<AbilitiesViewState, AbilitiesAction, AbilitiesNavigation, Nothing>(
    initialState = AbilitiesViewState()
) {

    private val _abilitiesPagingConfig = PagingConfig()
    private var abilitiesLoadingJob: Job? = null

    fun onAppear() {
        updateAbilitiesData()
    }

    override fun dispatch(action: AbilitiesAction) = when (action) {
        is AbilitiesAction.LoadNextPage -> fetchAbilities()
        is AbilitiesAction.AbilityClick -> emit(AbilitiesNavigation.NavigateToAbilityDetail(action.value))
    }

    private fun fetchAbilities() {
        if (_abilitiesPagingConfig.lastPageReached || abilitiesLoadingJob?.isActive == true) return

        abilitiesLoadingJob?.cancel()
        abilitiesLoadingJob = launchJob {
            abilitiesRepository.getAll(
                page = _abilitiesPagingConfig.page,
                pageSize = _abilitiesPagingConfig.pageSize,
            ) collectAndProcess {
                isLoading {
                    emit(viewState.value.copy(isLoading = true))
                }

                onSuccess { items ->
                    _abilitiesPagingConfig.lastPageReached = items.isNullOrEmpty()
                    _abilitiesPagingConfig.page++

                    items?.let {
                        val uiItems = viewState.value.items.plus(items)
                        emit(viewState.value.copy(items = uiItems, isLoading = false))
                    }
                }
            }
        }
    }

    private fun updateAbilitiesData() {
        launchJob {
            abilitiesRepository.getAll(
                page = 0,
                pageSize = 100
            ) collectAndProcess {
                onSuccess { items ->
                    val currentItems = viewState.value.items
                    currentItems.forEach { currentItem ->
                        items?.firstOrNull { item -> item.id == currentItem.id }?.let {
                            currentItem.experienceData = it.experienceData
                        }
                    }
                    emit(viewState.value.copy(items = currentItems))
                }
            }
        }
    }
}