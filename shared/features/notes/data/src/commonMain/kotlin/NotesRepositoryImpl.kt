import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.velkonost.getbetter.shared.core.datastore.extension.getUserToken
import com.velkonost.getbetter.shared.core.model.NoteType
import com.velkonost.getbetter.shared.core.util.ResultState
import com.velkonost.getbetter.shared.core.util.flowRequest
import com.velkonost.getbetter.shared.features.notes.api.NotesRepository
import com.velkonost.getbetter.shared.features.notes.api.model.Note
import com.velkonost.getbetter.shared.features.notes.api.model.SubNote
import kotlinx.coroutines.flow.Flow
import remote.NotesRemoteDataSource
import remote.model.request.CreateNewNoteRequest
import remote.model.request.EditNoteRequest
import remote.model.request.EditSubNoteRequest
import remote.model.request.SubNoteRequestData
import remote.model.request.UpdateNoteStateRequest
import remote.model.request.UpdateSubNoteStateRequest
import remote.model.response.KtorNote
import remote.model.response.asExternalModel

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
                areaId = areaId,
                subNotes = subNotes.map {
                    SubNoteRequestData(subNoteText = it.text)
                }
            )
            remoteDataSource.createNewNote(token, body)
        }
    )

    override fun editNote(
        noteId: Int,
        text: String,
        tags: List<String>,
        subNotes: List<SubNote>
    ): Flow<ResultState<Note>> = flowRequest(
        mapper = KtorNote::asExternalModel,
        request = {
            val token = localDataSource.getUserToken()
            val body = EditNoteRequest(
                noteId = noteId,
                noteText = text,
                noteTags = tags,
                subNotes = subNotes.map {
                    SubNoteRequestData(subNoteText = it.text)
                }
            )
            remoteDataSource.editNote(token, body)
        }
    )

    override fun editSubNote(
        noteId: Int,
        subNoteId: Int,
        subNoteText: String
    ): Flow<ResultState<Note>> = flowRequest(
        mapper = KtorNote::asExternalModel,
        request = {
            val token = localDataSource.getUserToken()
            val body = EditSubNoteRequest(
                noteId = noteId,
                subNoteId = subNoteId,
                subNoteText = subNoteText
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

    override fun getNoteDetails(noteId: Int): Flow<ResultState<Note>> = flowRequest(
        mapper = KtorNote::asExternalModel,
        request = {
            val token = localDataSource.getUserToken()
            remoteDataSource.getNoteDetails(token, noteId)
        }
    )

    override fun fetchUserDetails(page: Int, perPage: Int): Flow<ResultState<List<Note>>> =
        flowRequest(
            mapper = { this.map(KtorNote::asExternalModel) },
            request = {
                val token = localDataSource.getUserToken()
                remoteDataSource.getUserNotes(token, page, perPage)
            }
        )
}