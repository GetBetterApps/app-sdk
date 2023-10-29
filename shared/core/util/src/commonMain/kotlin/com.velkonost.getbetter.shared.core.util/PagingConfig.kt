package com.velkonost.getbetter.shared.core.util

const val PrefetchDistanceValue: Int = 2

data class PagingConfig(
    var isActive: Boolean = true,
    var page: Int = 0,
    val pageSize: Int = 5,
    val prefetchDistance: Int = PrefetchDistanceValue,
    var lastPageReached: Boolean = false
)