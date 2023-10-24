package remote

import com.velkonost.getbetter.shared.core.network.extensions.makeRequest
import com.velkonost.getbetter.shared.core.network.model.RemoteResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import remote.model.request.CreateNewNoteRequest
import remote.model.request.EditNoteRequest
import remote.model.request.EditSubNoteRequest
import remote.model.request.UpdateNoteStateRequest
import remote.model.request.UpdateSubNoteStateRequest
import remote.model.response.KtorNote

class NotesRemoteDataSource(
    private val httpClient: HttpClient
) {

    suspend fun createNewNote(
        token: String?,
        body: CreateNewNoteRequest
    ): RemoteResponse<KtorNote> = httpClient.post {
        makeRequest(
            path = Route.CREATE_NEW_NOTE,
            token = token,
            body = body
        )
    }.body()

    suspend fun editNote(
        token: String?,
        body: EditNoteRequest
    ): RemoteResponse<KtorNote> = httpClient.post {
        makeRequest(
            path = Route.EDIT_NOTE,
            token = token,
            body = body
        )
    }.body()

    suspend fun editSubNote(
        token: String?,
        body: EditSubNoteRequest
    ): RemoteResponse<KtorNote> = httpClient.post {
        makeRequest(
            path = Route.EDIT_SUB_NOTE,
            token = token,
            body = body
        )
    }.body()

    suspend fun deleteNote(
        token: String?,
        body: UpdateNoteStateRequest
    ): RemoteResponse<KtorNote> = httpClient.post {
        makeRequest(
            path = Route.DELETE_NOTE,
            token = token,
            body = body
        )
    }.body()

    suspend fun deleteSubNote(
        token: String?,
        body: UpdateSubNoteStateRequest
    ): RemoteResponse<KtorNote> = httpClient.post {
        makeRequest(
            path = Route.DELETE_SUB_NOTE,
            token = token,
            body = body
        )
    }.body()

    suspend fun completeGoal(
        token: String?,
        body: UpdateNoteStateRequest
    ): RemoteResponse<KtorNote> = httpClient.post {
        makeRequest(
            path = Route.COMPLETE_GOAL,
            token = token,
            body = body
        )
    }.body()

    suspend fun completeSubGoal(
        token: String?,
        body: UpdateSubNoteStateRequest
    ): RemoteResponse<KtorNote> = httpClient.post {
        makeRequest(
            path = Route.COMPLETE_SUB_GOAL,
            token = token,
            body = body
        )
    }.body()

    suspend fun getNoteDetails(
        token: String?,
        noteId: Int
    ): RemoteResponse<KtorNote> = httpClient.get {
        makeRequest(
            path = Route.GET_NOTE_DETAILS,
            token = token,
            params = mapOf("noteId" to noteId)
        )
    }.body()

    suspend fun getUserNotes(
        token: String?,
        page: Int,
        pageSize: Int
    ): RemoteResponse<List<KtorNote>> = httpClient.get {
        makeRequest(
            path = Route.GET_USER_NOTES,
            token = token,
            params = mapOf(
                "page" to page,
                "pageSize" to pageSize
            )
        )
    }.body()
}