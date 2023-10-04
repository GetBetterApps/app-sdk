package com.velkonost.getbetter.shared.features.addarea.presentation

import AreasRepository
import com.kuuurt.paging.multiplatform.Pager
import com.kuuurt.paging.multiplatform.PagingData
import com.kuuurt.paging.multiplatform.PagingResult
import com.kuuurt.paging.multiplatform.helpers.cachedIn
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import com.velkonost.getbetter.shared.core.model.response.FirestorePaginationInfo
import com.velkonost.getbetter.shared.core.util.ResultState
import com.velkonost.getbetter.shared.core.vm.BaseViewModel
import com.velkonost.getbetter.shared.features.addarea.presentation.contract.AddAreaAction
import com.velkonost.getbetter.shared.features.addarea.presentation.contract.AddAreaNavigation
import com.velkonost.getbetter.shared.features.addarea.presentation.contract.AddAreaViewState
import com.velkonost.getbetter.shared.features.addarea.presentation.model.PagingConfig
import com.velkonost.getbetter.shared.features.addarea.presentation.model.perPage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import model.Area
import model.pagination.PaginationParams

class AddAreaViewModel
internal constructor(
    private val areasRepository: AreasRepository
) : BaseViewModel<AddAreaViewState, AddAreaAction, AddAreaNavigation, Nothing>(
    initialState = AddAreaViewState()
) {

    private val _pager = Pager(
        clientScope = vmScope,
        config = PagingConfig,
        initialKey = FirestorePaginationInfo()
    ) { key, _ ->
        val params = PaginationParams(perPage, pagination = key)

        var pagingResult = PagingResult(
            items = emptyList<Area>(),
            currentKey = params.pagination,
            prevKey = { null },
            nextKey = { null },
        )

        val result = areasRepository.fetchPublicAreasToAdd(params.perPage, params.pagination)
        if (result is ResultState.Success) {
            pagingResult = PagingResult(
                items = result.data.items,
                currentKey = params.pagination,
                prevKey = { null },
                nextKey = {
                    if (result.data.items.isNotEmpty()) result.data.pagination
                    else null
                }
            )
        }

        pagingResult
    }

    @NativeCoroutines
    val pagingData: Flow<PagingData<Area>>
        get() = _pager.pagingData
            .distinctUntilChanged()
            .cachedIn(vmScope)

    override fun dispatch(action: AddAreaAction) = when (action) {
        else -> {}
    }

}