package com.velkonost.getbetter.shared.features.social.api

import com.velkonost.getbetter.shared.core.model.note.Note
import com.velkonost.getbetter.shared.core.util.ResultState
import kotlinx.coroutines.flow.Flow

interface SocialRepository {

    fun fetchAreasFeed(
        page: Int,
        pageSize: Int
    ): Flow<ResultState<List<Note>>>

    fun fetchGeneralFeed(
        page: Int,
        pageSize: Int
    ): Flow<ResultState<List<Note>>>

    suspend fun saveUpdatedNoteId(noteId: Int)

    suspend fun getUpdatedNoteId(): Flow<ResultState<Int>>
}