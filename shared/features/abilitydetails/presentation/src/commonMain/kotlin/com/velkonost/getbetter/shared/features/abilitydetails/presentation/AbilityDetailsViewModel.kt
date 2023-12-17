package com.velkonost.getbetter.shared.features.abilitydetails.presentation

import com.velkonost.getbetter.shared.core.model.task.Affirmation
import com.velkonost.getbetter.shared.core.util.PagingConfig
import com.velkonost.getbetter.shared.core.util.isLoading
import com.velkonost.getbetter.shared.core.util.onSuccess
import com.velkonost.getbetter.shared.core.vm.BaseViewModel
import com.velkonost.getbetter.shared.core.vm.SavedStateHandle
import com.velkonost.getbetter.shared.features.abilities.api.AbilitiesRepository
import com.velkonost.getbetter.shared.features.abilitydetails.presentation.contract.AbilityDetailsAction
import com.velkonost.getbetter.shared.features.abilitydetails.presentation.contract.AbilityDetailsNavigation
import com.velkonost.getbetter.shared.features.abilitydetails.presentation.contract.AbilityDetailsViewState
import com.velkonost.getbetter.shared.features.affirmations.api.AffirmationsRepository
import com.velkonost.getbetter.shared.features.notes.api.NotesRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest

class AbilityDetailsViewModel
internal constructor(
    savedStateHandle: SavedStateHandle,
    private val notesRepository: NotesRepository,
    private val abilitiesRepository: AbilitiesRepository,
    private val affirmationsRepository: AffirmationsRepository
) : BaseViewModel<AbilityDetailsViewState, AbilityDetailsAction, AbilityDetailsNavigation, Nothing>(
    initialState = AbilityDetailsViewState(),
    savedStateHandle = savedStateHandle
) {

    private val ability = savedStateHandle.ability.stateInWhileSubscribed(initialValue = null)
    private val isFavorite =
        savedStateHandle.isFavorite.stateInWhileSubscribed(initialValue = false)

    private val _notesPagingConfig = PagingConfig()
    private var notesLoadingJob: Job? = null

    private val favoritesJobsMap: HashMap<Int, Job> = hashMapOf()

    init {
        launchJob {
            isFavorite.collectLatest {
                emit(viewState.value.copy(isFavorite = it))

                ability.collectLatest { ability ->
                    emit(
                        viewState.value.copy(
                            isLoading = false,
                            ability = ability
                        )
                    )

                    if (!it) {
                        fetchAffirmations()
                        fetchNotes()
                    } else {
                        fetchFavoriteAffirmations()
                    }
                }
            }
        }
    }

    override fun dispatch(action: AbilityDetailsAction) = when (action) {
        is AbilityDetailsAction.UserNotesLoadNextPage -> fetchNotes()
        is AbilityDetailsAction.NavigateBack -> emit(action)
        is AbilityDetailsAction.FavoriteClick -> obtainFavoriteClick(action.value)
    }

    private fun fetchFavoriteAffirmations() {
        launchJob {
            affirmationsRepository.getFavoritesList() collectAndProcess {
                onSuccess { items ->
                    items?.let {
                        emit(viewState.value.copy(affirmations = items))
                    }
                }
            }
        }
    }

    private fun fetchAffirmations() {
        launchJob {
            viewState.value.ability?.id?.let { abilityId ->
                abilitiesRepository.getDetails(abilityId) collectAndProcess {
                    onSuccess { abilityDetails ->
                        abilityDetails?.let {
                            emit(viewState.value.copy(affirmations = abilityDetails.affirmations))
                        }
                    }
                }
            }
        }
    }

    private fun fetchNotes() {
        if (_notesPagingConfig.lastPageReached || notesLoadingJob?.isActive == true) return

        notesLoadingJob?.cancel()
        notesLoadingJob = launchJob {
            viewState.value.ability?.id?.let { abilityId ->
                notesRepository.fetchNotesByAbility(
                    abilityId = abilityId,
                    page = _notesPagingConfig.page,
                    pageSize = _notesPagingConfig.pageSize
                ) collectAndProcess {
                    isLoading {
                        val notesViewState =
                            viewState.value.userNotesViewState.copy(isLoading = true)
                        emit(viewState.value.copy(userNotesViewState = notesViewState))
                    }
                    onSuccess { list ->
                        _notesPagingConfig.lastPageReached = list.isNullOrEmpty()
                        _notesPagingConfig.page++

                        list?.let {
                            val allItems = viewState.value.userNotesViewState.items.plus(list)
                            val notesViewState = viewState.value.userNotesViewState.copy(
                                isLoading = false,
                                items = allItems
                            )
                            emit(viewState.value.copy(userNotesViewState = notesViewState))
                        }
                    }
                }
            }
        }
    }

    private fun obtainFavoriteClick(value: Affirmation) {
        if (favoritesJobsMap.containsKey(value.id)) return

        launchJob {
            affirmationsRepository.updateFavorite(
                affirmationId = value.id,
                isFavorite = !value.isFavorite
            ) collectAndProcess {
                onSuccess {
                    val affirmations = viewState.value.affirmations.toMutableList()
                    val indexOfChangedItem = affirmations.indexOf(value)
                    affirmations[indexOfChangedItem] =
                        affirmations[indexOfChangedItem].copy(isFavorite = !value.isFavorite)
                    emit(viewState.value.copy(affirmations = affirmations.toList()))

                    favoritesJobsMap.remove(value.id)
                }
            }
        }.also {
            favoritesJobsMap[value.id] = it
        }.invokeOnCompletion {

        }
    }

}
