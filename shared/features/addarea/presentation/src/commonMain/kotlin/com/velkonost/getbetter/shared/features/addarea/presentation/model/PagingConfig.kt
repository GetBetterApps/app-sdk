package com.velkonost.getbetter.shared.features.addarea.presentation.model

import com.kuuurt.paging.multiplatform.PagingConfig

const val perPage = 1
val PagingConfig = PagingConfig(pageSize = perPage, prefetchDistance = 8, initialLoadSize = perPage)