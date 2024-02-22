package com.velkonost.getbetter.shared.features.subscription.data.remote

object Route {
    private const val PREFIX = "subscription"

    const val START_TRIAL = "$PREFIX/startTrial"
    const val CREATE = "$PREFIX/create"
    const val STATUS = "$PREFIX/status"
    const val CANCEL = "$PREFIX/cancel"

    const val AVAILABLE = "$PREFIX/available"
    const val IS_AREAS_LIMIT_REACHED = "$PREFIX/areasLimit"
}