package com.velkonost.getbetter.shared.features.areadetail.api

interface AreaDetailRepository {

    suspend fun shouldShowHint(): Boolean

}