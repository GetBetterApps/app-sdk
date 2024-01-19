package com.velkonost.getbetter.shared.core.util

const val PrefetchDistanceValue: Int = 4

data class PagingConfig(
    var page: Int = 0,
    val pageSize: Int = 10,
    val prefetchDistance: Int = PrefetchDistanceValue,
    var lastPageReached: Boolean = false
)

fun PagingConfig.reset() {
    page = 0
    lastPageReached = false
}