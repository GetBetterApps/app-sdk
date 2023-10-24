package com.velkonost.getbetter.shared.core.util

const val PrefetchDistanceValue: Int = 8

data class PagingConfig(
    var page: Int = 0,
    val pageSize: Int = 1,
    val prefetchDistance: Int = PrefetchDistanceValue,
    var lastPageReached: Boolean = false
)