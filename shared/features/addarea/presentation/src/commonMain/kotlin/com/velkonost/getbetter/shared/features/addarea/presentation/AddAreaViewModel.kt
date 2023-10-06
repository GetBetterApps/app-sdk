package com.velkonost.getbetter.shared.features.addarea.presentation

import AreasRepository
import com.velkonost.getbetter.shared.core.util.PagingConfig
import com.velkonost.getbetter.shared.core.util.ResultState
import com.velkonost.getbetter.shared.core.vm.BaseViewModel
import com.velkonost.getbetter.shared.features.addarea.presentation.contract.AddAreaAction
import com.velkonost.getbetter.shared.features.addarea.presentation.contract.AddAreaNavigation
import com.velkonost.getbetter.shared.features.addarea.presentation.contract.AddAreaViewState
import com.velkonost.getbetter.shared.features.addarea.presentation.contract.LoadNextPage
import com.velkonost.getbetter.shared.features.addarea.presentation.model.getUserTermsOfMembership
import com.velkonost.getbetter.shared.features.addarea.presentation.model.toUI
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
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
    }

    private fun fetchAreas() {
        if (_areasPagingConfig.lastPageReached) return

        launchJob {
            emit(viewState.value.copy(isLoading = true))

            val result = areasRepository.fetchPublicAreasToAdd(
                perPage = _areasPagingConfig.pageSize,
                lastElement = _areasPagingConfig.lastElement
            )

            if (result is ResultState.Success) {
                if (result.data.items.isEmpty()) {
                    _areasPagingConfig.lastPageReached = true
                }

                _areasPagingConfig.lastElement = result.data.lastElement

                val userId = Firebase.auth.currentUser!!.uid
                val items = viewState.value.items.plus(
                    result.data.items.map {
                        val areaTermsOfMembership = it.getUserTermsOfMembership(userId)
                        it.toUI(areaTermsOfMembership)
                    }
                )
                emit(viewState.value.copy(items = items))
            }

            if (result is ResultState.Failure) {
                _areasPagingConfig.lastPageReached = true
            }

            emit(viewState.value.copy(isLoading = false))
        }
    }
}