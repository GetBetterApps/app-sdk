package com.velkonost.getbetter.shared.features.social

import com.velkonost.getbetter.shared.core.model.note.Note
import com.velkonost.getbetter.shared.core.util.PagingConfig
import com.velkonost.getbetter.shared.core.util.isLoading
import com.velkonost.getbetter.shared.core.util.onSuccess
import com.velkonost.getbetter.shared.core.vm.BaseViewModel
import com.velkonost.getbetter.shared.core.vm.extension.onFailureWithMsg
import com.velkonost.getbetter.shared.features.notes.api.NotesRepository
import com.velkonost.getbetter.shared.features.social.api.SocialRepository
import com.velkonost.getbetter.shared.features.social.contracts.NavigateToNoteDetail
import com.velkonost.getbetter.shared.features.social.contracts.SocialAction
import com.velkonost.getbetter.shared.features.social.contracts.SocialNavigation
import com.velkonost.getbetter.shared.features.social.contracts.SocialViewState
import kotlinx.coroutines.Job

class SocialViewModel
internal constructor(
    private val socialRepository: SocialRepository,
    private val notesRepository: NotesRepository
) : BaseViewModel<SocialViewState, SocialAction, SocialNavigation, Nothing>(
    initialState = SocialViewState()
) {

    private val _generalFeedPagingConfig = PagingConfig()
    private val _areasFeedPagingConfig = PagingConfig()

    private var generalFeedLoadingJob: Job? = null
    private var areasFeedLoadingJob: Job? = null

    fun onAppear() {
        checkUpdatedNote()
    }

    override fun dispatch(action: SocialAction) = when (action) {
        is SocialAction.GeneralFeedLoadNextPage -> fetchGeneralFeed()
        is SocialAction.AreasFeedLoadNextPage -> fetchAreasFeed()
        is SocialAction.NoteClick -> obtainNoteClick(action.value)
        is SocialAction.RefreshGeneralFeed -> obtainRefreshGeneralFeed()
        is SocialAction.RefreshAreasFeed -> obtainRefreshAreasFeed()
    }

    private fun obtainRefreshGeneralFeed() {
        _generalFeedPagingConfig.page = 0
        _generalFeedPagingConfig.lastPageReached = false
        fetchGeneralFeed(refreshList = true)
    }

    private fun obtainRefreshAreasFeed() {
        _areasFeedPagingConfig.page = 0
        _areasFeedPagingConfig.lastPageReached = false
        fetchAreasFeed(refreshList = true)
    }

    private fun obtainNoteClick(value: Note) {
        launchJob {
            socialRepository.saveUpdatedNoteId(value.id)
            emit(NavigateToNoteDetail(value))
        }

    }

    private fun checkUpdatedNote() {
        launchJob {
            socialRepository.getUpdatedNoteId()
                .collect { updatedNoteResult ->
                    updatedNoteResult.onSuccess { noteId ->
                        noteId?.let { refreshNoteData(it) }
                    }
                }
        }
    }

    private fun refreshNoteData(noteId: Int) {
        launchJob {
            notesRepository.getNoteDetails(noteId)
                .collect { noteDetailsResult ->
                    noteDetailsResult.onSuccess { note ->
                        note?.let {
                            val indexOfChangedItemInGeneralFeed =
                                viewState.value.generalFeed.items.indexOfFirst { it.id == note.id }
                            val indexOfChangedItemInAreasFeed =
                                viewState.value.areasFeed.items.indexOfFirst { it.id == note.id }

                            val allItemsGeneralFeed =
                                viewState.value.generalFeed.items.toMutableList()
                            val allItemsAreasFeed =
                                viewState.value.areasFeed.items.toMutableList()

                            when {
                                indexOfChangedItemInGeneralFeed == -1 -> {
                                    allItemsGeneralFeed.add(0, note)
                                }

                                !note.isActive -> {
                                    allItemsGeneralFeed.removeAt(indexOfChangedItemInGeneralFeed)
                                }

                                else -> {
                                    allItemsGeneralFeed[indexOfChangedItemInGeneralFeed] = note
                                }
                            }

                            when {
                                indexOfChangedItemInAreasFeed == -1 -> {
                                    allItemsAreasFeed.add(0, note)
                                }

                                !note.isActive -> {
                                    allItemsAreasFeed.removeAt(indexOfChangedItemInAreasFeed)
                                }

                                else -> {
                                    allItemsAreasFeed[indexOfChangedItemInAreasFeed] = note
                                }
                            }

                            allItemsGeneralFeed.filter { it.area.id == note.area.id }.forEach {
                                it.area = note.area
                            }
                            allItemsAreasFeed.filter { it.area.id == note.area.id }.forEach {
                                it.area = note.area
                            }

                            val generalFeedViewState =
                                viewState.value.generalFeed.copy(
                                    items = allItemsGeneralFeed
                                )
                            val areasFeedViewState =
                                viewState.value.areasFeed.copy(
                                    items = allItemsAreasFeed
                                )

                            emit(
                                viewState.value.copy(
                                    generalFeed = generalFeedViewState,
                                    areasFeed = areasFeedViewState
                                )
                            )
                        }
                    }
                }
        }
    }

    private fun fetchGeneralFeed(refreshList: Boolean = false) {
        if (_generalFeedPagingConfig.lastPageReached || generalFeedLoadingJob?.isActive == true) return

        generalFeedLoadingJob?.cancel()
        generalFeedLoadingJob = launchJob {
            socialRepository.fetchGeneralFeed(
                page = _generalFeedPagingConfig.page,
                pageSize = _generalFeedPagingConfig.pageSize
            ).collect { result ->
                with(result) {
                    isLoading {
                        val generalFeedViewState =
                            viewState.value.generalFeed.copy(isLoading = true)
                        emit(viewState.value.copy(generalFeed = generalFeedViewState))
                    }
                    onSuccess { items ->
                        _generalFeedPagingConfig.lastPageReached = items.isNullOrEmpty()
                        _generalFeedPagingConfig.page++

                        items?.let {
                            val allItems =
                                if (refreshList) it
                                else viewState.value.generalFeed.items.plus(it)
                            val generalFeedViewState = viewState.value.generalFeed.copy(
                                isLoading = false,
                                items = allItems
                            )
                            emit(viewState.value.copy(generalFeed = generalFeedViewState))
                        }
                    }
                    onFailureWithMsg { _, message ->
                        message?.let { emit(it) }
                    }
                }
            }
        }
    }

    private fun fetchAreasFeed(refreshList: Boolean = false) {
        if (_areasFeedPagingConfig.lastPageReached || areasFeedLoadingJob?.isActive == true) return

        areasFeedLoadingJob?.cancel()
        areasFeedLoadingJob = launchJob {
            socialRepository.fetchAreasFeed(
                page = _areasFeedPagingConfig.page,
                pageSize = _areasFeedPagingConfig.pageSize
            ).collect { result ->
                with(result) {
                    isLoading {
                        val areasFeedViewState = viewState.value.areasFeed.copy(isLoading = true)
                        emit(viewState.value.copy(areasFeed = areasFeedViewState))
                    }
                    onSuccess { items ->
                        _areasFeedPagingConfig.lastPageReached = items.isNullOrEmpty()
                        _areasFeedPagingConfig.page++

                        items?.let {
                            val allItems =
                                if (refreshList) it
                                else viewState.value.areasFeed.items.plus(it)
                            val areasFeedViewState = viewState.value.areasFeed.copy(
                                isLoading = false,
                                items = allItems
                            )
                            emit(viewState.value.copy(areasFeed = areasFeedViewState))
                        }
                    }
                    onFailureWithMsg { _, message ->
                        message?.let { emit(it) }
                    }
                }

            }
        }
    }

}