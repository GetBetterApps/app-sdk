package com.velkonost.getbetter.shared.features.diary.model

import com.kuuurt.paging.multiplatform.PagingConfig

internal val PagingConfig = PagingConfig(pageSize = 20, prefetchDistance = 8, initialLoadSize = 30)