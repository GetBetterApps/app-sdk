package com.velkonost.getbetter.shared.features.notedetail.api

interface NoteDetailRepository {

    suspend fun shouldShowCommentsHint(): Boolean

    suspend fun shouldShowNoteHint(): Boolean
}