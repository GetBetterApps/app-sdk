package com.velkonost.getbetter.shared.features.createnote.api

interface CreateNoteRepository {

    suspend fun shouldShowHint(): Boolean

}