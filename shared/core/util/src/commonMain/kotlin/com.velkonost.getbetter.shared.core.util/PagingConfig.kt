package com.velkonost.getbetter.shared.core.util

const val prefetchDistanceValue: Int = 8

data class PagingConfig<T>(
    val pageSize: Int = 1,
    val prefetchDistance: Int = prefetchDistanceValue,
    var lastElement: T? = null,
    var lastPageReached: Boolean = false
)