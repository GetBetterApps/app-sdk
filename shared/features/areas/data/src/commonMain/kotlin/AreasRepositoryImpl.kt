import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.velkonost.getbetter.shared.core.datastore.extension.getUserToken
import com.velkonost.getbetter.shared.core.util.ResultState
import com.velkonost.getbetter.shared.core.util.flowRequest
import kotlinx.coroutines.flow.Flow
import model.Area
import remote.AreasRemoteDataSource
import remote.model.request.CreateNewAreaRequest
import remote.model.request.UpdateAreaStateRequest
import remote.model.response.KtorArea
import remote.model.response.asExternalModel

class AreasRepositoryImpl
constructor(
    private val remoteDataSource: AreasRemoteDataSource,
    private val localDataSource: DataStore<Preferences>
) : AreasRepository {

    override fun createNewArea(
        name: String,
        description: String,
        isPrivate: Boolean,
        requiredLevel: Int,
        emojiId: Int?,
        imageUrl: String?
    ): Flow<ResultState<Area>> = flowRequest(
        mapper = KtorArea::asExternalModel,
        request = {
            val token = localDataSource.getUserToken()
            val body = CreateNewAreaRequest(
                areaName = name,
                areaDescription = description,
                isAreaPrivate = isPrivate,
                areaRequiredLevel = requiredLevel,
                areaEmojiId = emojiId,
                areaImageUrl = imageUrl
            )
            remoteDataSource.createNewArea(token, body)
        }
    )

    override fun editArea(
        id: Int,
        name: String,
        description: String,
        emojiId: Int?,
        imageUrl: String?
    ): Flow<ResultState<Area>> = flowRequest(
        mapper = KtorArea::asExternalModel,
        request = {
            val token = localDataSource.getUserToken()
            val body = CreateNewAreaRequest(
                areaId = id,
                areaName = name,
                areaDescription = description,
                areaEmojiId = emojiId,
                areaImageUrl = imageUrl
            )
            remoteDataSource.editArea(token, body)
        }
    )

    override fun leaveArea(areaId: Int): Flow<ResultState<Area>> = flowRequest(
        mapper = KtorArea::asExternalModel,
        request = {
            val token = localDataSource.getUserToken()
            val body = UpdateAreaStateRequest(areaId)
            remoteDataSource.leaveArea(token, body)
        }
    )

    override fun deleteArea(areaId: Int): Flow<ResultState<Area>> = flowRequest(
        mapper = KtorArea::asExternalModel,
        request = {
            val token = localDataSource.getUserToken()
            val body = UpdateAreaStateRequest(areaId)
            remoteDataSource.deleteArea(token, body)
        }
    )

    override fun addUserArea(areaId: Int): Flow<ResultState<Area>> = flowRequest(
        mapper = KtorArea::asExternalModel,
        request = {
            val token = localDataSource.getUserToken()
            val body = UpdateAreaStateRequest(areaId)
            remoteDataSource.addUserArea(token, body)
        }
    )

    override fun fetchAreaDetails(areaId: Int): Flow<ResultState<Area>> = flowRequest(
        mapper = KtorArea::asExternalModel,
        request = {
            val token = localDataSource.getUserToken()
            remoteDataSource.getAreaDetails(token, areaId)
        }
    )

    override fun fetchAllAreas(
        page: Int,
        perPage: Int
    ): Flow<ResultState<List<Area>>> = flowRequest(
        mapper = { map(KtorArea::asExternalModel) },
        request = {
            val token = localDataSource.getUserToken()
            remoteDataSource.getAllAreas(token, page, perPage)
        }
    )

    override fun fetchUserAreas(): Flow<ResultState<List<Area>>> = flowRequest(
        mapper = { map(KtorArea::asExternalModel) },
        request = {
            val token = localDataSource.getUserToken()
            remoteDataSource.getUserAreas(token)
        }
    )

    override suspend fun fetchPublicAreasToAdd(
        page: Int,
        perPage: Int
    ): Flow<ResultState<List<Area>>> = flowRequest(
        mapper = { map(KtorArea::asExternalModel) },
        request = {
            val token = localDataSource.getUserToken()
            remoteDataSource.getPublicAreas(token, page, perPage)
        }
    )
}