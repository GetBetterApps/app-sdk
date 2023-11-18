package com.velkonost.getbetter.shared.features.notes.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.velkonost.getbetter.shared.core.datastore.extension.getUserToken
import com.velkonost.getbetter.shared.core.model.note.Note
import com.velkonost.getbetter.shared.core.model.note.NoteType
import com.velkonost.getbetter.shared.core.model.note.SubNote
import com.velkonost.getbetter.shared.core.util.ResultState
import com.velkonost.getbetter.shared.core.util.flowRequest
import com.velkonost.getbetter.shared.features.notes.api.NotesRepository
import com.velkonost.getbetter.shared.features.notes.data.remote.NotesRemoteDataSource
import com.velkonost.getbetter.shared.features.notes.data.remote.model.request.CreateNewNoteRequest
import com.velkonost.getbetter.shared.features.notes.data.remote.model.request.EditNoteRequest
import com.velkonost.getbetter.shared.features.notes.data.remote.model.request.EditSubNoteRequest
import com.velkonost.getbetter.shared.features.notes.data.remote.model.request.SubNoteRequestData
import com.velkonost.getbetter.shared.features.notes.data.remote.model.request.UpdateNoteStateRequest
import com.velkonost.getbetter.shared.features.notes.data.remote.model.request.UpdateSubNoteStateRequest
import com.velkonost.getbetter.shared.features.notes.data.remote.model.response.KtorNote
import com.velkonost.getbetter.shared.features.notes.data.remote.model.response.asExternalModel
import kotlinx.coroutines.flow.Flow

class NotesRepositoryImpl
constructor(
    private val remoteDataSource: NotesRemoteDataSource,
    private val localDataSource: DataStore<Preferences>
) : NotesRepository {

    override fun createNewNote(
        noteType: NoteType,
        text: String,
        tags: List<String>,
        isPrivate: Boolean,
        expectedCompletionDate: Long?,
        areaId: Int,
        subNotes: List<SubNote>
    ): Flow<ResultState<Note>> = flowRequest(
        mapper = KtorNote::asExternalModel,
        request = {
            val token = localDataSource.getUserToken()
            val body = CreateNewNoteRequest(
                noteType = noteType.responseName,
                noteText = text,
                noteTags = tags,
                isNotePrivate = isPrivate,
                noteExpectedCompletionDate = expectedCompletionDate,
                areaId = areaId,
                subNotes = subNotes.map {
                    SubNoteRequestData(
                        subNoteText = it.text,
                        completionDate = it.completionDate,
                        expectedCompletionDate = it.expectedCompletionDate
                    )
                }
            )
            remoteDataSource.createNewNote(token, body)
        }
    )

    override fun editNote(
        noteId: Int,
        text: String,
        tags: List<String>,
        completionDate: Long?,
        expectedCompletionDate: Long?,
        subNotes: List<SubNote>
    ): Flow<ResultState<Note>> = flowRequest(
        mapper = KtorNote::asExternalModel,
        request = {
            val token = localDataSource.getUserToken()
            val body = EditNoteRequest(
                noteId = noteId,
                noteText = text,
                noteTags = tags,
                noteMedia = emptyList(),
                completionDate = completionDate,
                expectedCompletionDate = expectedCompletionDate,
                subNotes = subNotes.map {
                    SubNoteRequestData(
                        subNoteText = it.text,
                        completionDate = it.completionDate,
                        expectedCompletionDate = it.expectedCompletionDate
                    )
                }
            )
            remoteDataSource.editNote(token, body)
        }
    )

    override fun editSubNote(
        noteId: Int,
        subNoteId: Int,
        subNoteText: String,
        subNoteCompletionDate: Long?,
        subNoteExpectedCompletionDate: Long?
    ): Flow<ResultState<Note>> = flowRequest(
        mapper = KtorNote::asExternalModel,
        request = {
            val token = localDataSource.getUserToken()
            val body = EditSubNoteRequest(
                noteId = noteId,
                subNoteId = subNoteId,
                subNoteText = subNoteText,
                subNoteCompletionDate = subNoteCompletionDate,
                subNoteExpectedCompletionDate = subNoteExpectedCompletionDate
            )
            remoteDataSource.editSubNote(token, body)
        }
    )

    override fun deleteNote(noteId: Int): Flow<ResultState<Note>> = flowRequest(
        mapper = KtorNote::asExternalModel,
        request = {
            val token = localDataSource.getUserToken()
            val body = UpdateNoteStateRequest(
                noteId = noteId
            )
            remoteDataSource.deleteNote(token, body)
        }
    )

    override fun deleteSubNote(noteId: Int, subNoteId: Int): Flow<ResultState<Note>> = flowRequest(
        mapper = KtorNote::asExternalModel,
        request = {
            val token = localDataSource.getUserToken()
            val body = UpdateSubNoteStateRequest(
                noteId = noteId,
                subNoteId = subNoteId
            )
            remoteDataSource.deleteSubNote(token, body)
        }
    )

    override fun completeGoal(noteId: Int): Flow<ResultState<Note>> = flowRequest(
        mapper = KtorNote::asExternalModel,
        request = {
            val token = localDataSource.getUserToken()
            val body = UpdateNoteStateRequest(
                noteId = noteId
            )
            remoteDataSource.completeGoal(token, body)
        }
    )

    override fun unCompleteGoal(noteId: Int): Flow<ResultState<Note>> = flowRequest(
        mapper = KtorNote::asExternalModel,
        request = {
            val token = localDataSource.getUserToken()
            val body = UpdateNoteStateRequest(
                noteId = noteId
            )
            remoteDataSource.unCompleteGoal(token, body)
        }
    )

    override fun completeSubGoal(noteId: Int, subNoteId: Int): Flow<ResultState<Note>> =
        flowRequest(
            mapper = KtorNote::asExternalModel,
            request = {
                val token = localDataSource.getUserToken()
                val body = UpdateSubNoteStateRequest(
                    noteId = noteId,
                    subNoteId = subNoteId
                )
                remoteDataSource.completeSubGoal(token, body)
            }
        )

    override fun unCompleteSubGoal(noteId: Int, subNoteId: Int): Flow<ResultState<Note>> =
        flowRequest(
            mapper = KtorNote::asExternalModel,
            request = {
                val token = localDataSource.getUserToken()
                val body = UpdateSubNoteStateRequest(
                    noteId = noteId,
                    subNoteId = subNoteId
                )
                remoteDataSource.unCompleteSubGoal(token, body)
            }
        )

    override fun getNoteDetails(noteId: Int): Flow<ResultState<Note>> = flowRequest(
        mapper = KtorNote::asExternalModel,
        request = {
            val token = localDataSource.getUserToken()
            remoteDataSource.getNoteDetails(token, noteId)
        }
    )

    override fun fetchUserNotes(page: Int, perPage: Int): Flow<ResultState<List<Note>>> =
        flowRequest(
            mapper = { this.map(KtorNote::asExternalModel) },
            request = {
                val token = localDataSource.getUserToken()
                remoteDataSource.getUserNotes(token, page, perPage)
            }
        )

    override fun fetchOtherUserNotes(
        userId: String,
        page: Int,
        pageSize: Int
    ): Flow<ResultState<List<Note>>> = flowRequest(
        mapper = { this.map(KtorNote::asExternalModel) },
        request = {
            val token = localDataSource.getUserToken()
            remoteDataSource.getOtherUserNotes(token, userId, page, pageSize)
        }
    )
}