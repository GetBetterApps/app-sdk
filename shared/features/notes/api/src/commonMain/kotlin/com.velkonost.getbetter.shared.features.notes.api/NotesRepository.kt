package com.velkonost.getbetter.shared.features.notes.api

import com.velkonost.getbetter.shared.core.model.note.Note
import com.velkonost.getbetter.shared.core.model.note.NoteType
import com.velkonost.getbetter.shared.core.model.note.SubNote
import com.velkonost.getbetter.shared.core.util.ResultState
import kotlinx.coroutines.flow.Flow

interface NotesRepository {

    fun createNewNote(
        noteType: NoteType,
        text: String,
        tags: List<String>,
        isPrivate: Boolean,
        expectedCompletionDate: Long?,
        areaId: Int,
        taskId: Int?,
        subNotes: List<SubNote>
    ): Flow<ResultState<Note>>

    fun editNote(
        noteId: Int,
        text: String,
        tags: List<String>,
        completionDate: Long?,
        expectedCompletionDate: Long?,
        subNotes: List<SubNote>,
    ): Flow<ResultState<Note>>

    fun editSubNote(
        noteId: Int,
        subNoteId: Int,
        subNoteText: String,
        subNoteCompletionDate: Long?,
        subNoteExpectedCompletionDate: Long?
    ): Flow<ResultState<Note>>

    fun deleteNote(
        noteId: Int
    ): Flow<ResultState<Note>>

    fun deleteSubNote(
        noteId: Int,
        subNoteId: Int
    ): Flow<ResultState<Note>>

    fun completeGoal(
        noteId: Int
    ): Flow<ResultState<Note>>

    fun unCompleteGoal(
        noteId: Int
    ): Flow<ResultState<Note>>

    fun completeSubGoal(
        noteId: Int,
        subNoteId: Int
    ): Flow<ResultState<Note>>

    fun unCompleteSubGoal(
        noteId: Int,
        subNoteId: Int
    ): Flow<ResultState<Note>>

    fun getNoteDetails(
        noteId: Int
    ): Flow<ResultState<Note>>

    fun fetchUserNotes(
        page: Int,
        perPage: Int
    ): Flow<ResultState<List<Note>>>

    fun fetchOtherUserNotes(
        userId: String,
        page: Int,
        pageSize: Int
    ): Flow<ResultState<List<Note>>>

}