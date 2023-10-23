package com.velkonost.getbetter.shared.features.addarea.presentation

import AreasRepository
import com.velkonost.getbetter.shared.core.model.TermsOfMembership
import com.velkonost.getbetter.shared.core.util.PagingConfig
import com.velkonost.getbetter.shared.core.util.isLoading
import com.velkonost.getbetter.shared.core.util.onSuccess
import com.velkonost.getbetter.shared.core.vm.BaseViewModel
import com.velkonost.getbetter.shared.core.vm.extension.onFailureWithMsg
import com.velkonost.getbetter.shared.features.addarea.presentation.contract.AddAreaAction
import com.velkonost.getbetter.shared.features.addarea.presentation.contract.AddAreaClick
import com.velkonost.getbetter.shared.features.addarea.presentation.contract.AddAreaNavigation
import com.velkonost.getbetter.shared.features.addarea.presentation.contract.AddAreaViewState
import com.velkonost.getbetter.shared.features.addarea.presentation.contract.AreaChanged
import com.velkonost.getbetter.shared.features.addarea.presentation.contract.LoadNextPage
import com.velkonost.getbetter.shared.features.addarea.presentation.contract.NavigateBack
import com.velkonost.getbetter.shared.features.addarea.presentation.model.toUI
import dev.gitlive.firebase.firestore.DocumentSnapshot

class AddAreaViewModel
internal constructor(
    private val areasRepository: AreasRepository
) : BaseViewModel<AddAreaViewState, AddAreaAction, AddAreaNavigation, Nothing>(
    initialState = AddAreaViewState()
) {

    private val _areasPagingConfig = PagingConfig<DocumentSnapshot>()

    init {
        fetchAreas()
    }

    override fun dispatch(action: AddAreaAction) = when (action) {
        is LoadNextPage -> fetchAreas()
        is AddAreaClick -> obtainAddAreaClick(action.areaId)
        is AreaChanged -> obtainAreaChanged(action.areaId)
        is NavigateBack -> emit(action)
    }

    private fun fetchAreas() {
        if (_areasPagingConfig.lastPageReached) return

        launchJob {
            areasRepository.fetchPublicAreasToAdd(
                page = _areasPagingConfig.page,
                perPage = _areasPagingConfig.pageSize,
            ).collect { result ->
                with(result) {
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

                    onFailureWithMsg { _, message ->
                        _areasPagingConfig.lastPageReached = true
                        message?.let { emit(it) }
                    }
                }
            }
        }
    }

    private fun obtainAddAreaClick(areaId: Int) {
        launchJob {
            areasRepository.addUserArea(areaId).collect { result ->
                with(result) {
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

                    onFailureWithMsg { _, message ->
                        message?.let { emit(it) }
                    }
                }
            }
        }
    }

    private fun obtainAreaChanged(areaId: Int) {
        launchJob {
            areasRepository.fetchAreaDetails(areaId).collect { result ->
                with(result) {
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
                    onFailureWithMsg { _, message ->
                        message?.let { emit(it) }
                        val items = viewState.value.items
                        val areaIndex = items.indexOfFirst { area -> area.id == areaId }
                        emit(viewState.value.copy(items = items.minus(items[areaIndex])))
                    }
                }

            }
        }
    }
}