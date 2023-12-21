package com.velkonost.getbetter.shared.features.createnote.api

interface CreateNoteRepository {

    suspend fun shouldShowNoteHint(): Boolean

    suspend fun shouldShowGoalHint(): Boolean

}