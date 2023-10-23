package remote

import com.velkonost.getbetter.shared.core.network.extensions.makeRequest
import com.velkonost.getbetter.shared.core.network.model.RemoteResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import remote.model.request.CreateNewAreaRequest
import remote.model.request.UpdateAreaStateRequest
import remote.model.response.KtorArea

class AreasRemoteDataSource(
    private val httpClient: HttpClient
) {

    suspend fun createNewArea(
        token: String?,
        body: CreateNewAreaRequest
    ): RemoteResponse<KtorArea> = httpClient.post {
        makeRequest(
            path = Route.CREATE_NEW_AREA,
            token = token,
            body = body
        )
    }.body()

    suspend fun editArea(
        token: String?,
        body: CreateNewAreaRequest
    ): RemoteResponse<KtorArea> = httpClient.post {
        makeRequest(
            path = Route.EDIT_AREA,
            token = token,
            body = body
        )
    }.body()

    suspend fun leaveArea(
        token: String?,
        body: UpdateAreaStateRequest
    ): RemoteResponse<List<KtorArea>> = httpClient.post {
        makeRequest(
            path = Route.LEAVE_AREA,
            token = token,
            body = body
        )
    }.body()

    suspend fun deleteArea(
        token: String?,
        body: UpdateAreaStateRequest
    ): RemoteResponse<List<KtorArea>> = httpClient.post {
        makeRequest(
            path = Route.DELETE_AREA,
            token = token,
            body = body
        )
    }.body()

    suspend fun addUserArea(
        token: String?,
        body: UpdateAreaStateRequest
    ): RemoteResponse<List<KtorArea>> = httpClient.post {
        makeRequest(
            path = Route.ADD_USER_AREA,
            token = token,
            body = body
        )
    }.body()

    suspend fun getAreaDetails(
        token: String?,
        areaId: Int
    ): RemoteResponse<KtorArea> = httpClient.engine

}