package com.velkonost.getbetter.shared.core.util

const val PrefetchDistanceValue: Int = 2

data class PagingConfig(
    var page: Int = 0,
    val pageSize: Int = 5,
    val prefetchDistance: Int = PrefetchDistanceValue,
    var lastPageReached: Boolean = false
)

fun PagingConfig.reset() {
    page = 0
    lastPageReached = false
}