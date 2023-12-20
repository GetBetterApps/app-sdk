package com.velkonost.getbetter.shared.features.addarea.api

interface AddAreaRepository {

    suspend fun shouldShowHint(): Boolean

}