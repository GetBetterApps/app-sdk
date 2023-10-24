package com.velkonost.getbetter.shared.features.notes.api

import com.velkonost.getbetter.shared.core.model.NoteType
import com.velkonost.getbetter.shared.core.util.ResultState
import com.velkonost.getbetter.shared.features.notes.api.model.Note
import com.velkonost.getbetter.shared.features.notes.api.model.SubNote
import kotlinx.coroutines.flow.Flow

interface NotesRepository {

    fun createNewNote(
        noteType: NoteType,
        text: String,
        tags: List<String>,
        isPrivate: Boolean,
        areaId: Int,
        subNotes: List<SubNote>
    ): Flow<ResultState<Note>>

    fun editNote(
        noteId: Int,
        text: String,
        tags: List<String>,
        subNotes: List<SubNote>
    ): Flow<ResultState<Note>>

    fun editSubNote(
        noteId: Int,
        subNoteId: Int,
        subNoteText: String
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

    fun completeSubGoal(
        noteId: Int,
        subNoteId: Int
    ): Flow<ResultState<Note>>

    fun getNoteDetails(
        noteId: Int
    ): Flow<ResultState<Note>>

    fun fetchUserDetails(
        page: Int,
        perPage: Int
    ): Flow<ResultState<List<Note>>>

}