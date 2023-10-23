import com.velkonost.getbetter.shared.core.util.ResultState
import kotlinx.coroutines.flow.Flow
import model.Area

interface AreasRepository {

    fun fetchAllAreas(
        page: Int,
        perPage: Int
    ): Flow<ResultState<List<Area>>>

    fun editArea(
        id: String,
        name: String,
        description: String,
        emojiId: Int? = null,
        imageUrl: String? = null
    ): Flow<ResultState<Area>>

    fun createNewArea(
        name: String,
        description: String,
        isPrivate: Boolean,
        requiredLevel: Int,
        emojiId: Int? = null,
        imageUrl: String? = null
    ): Flow<ResultState<Area>>

    fun deleteArea(areaId: Int): Flow<ResultState<List<Area>>>

    fun leaveArea(areaId: Int): Flow<ResultState<List<Area>>>

    fun fetchUserAreas(): Flow<ResultState<List<Area>>>

    fun addUserArea(areaId: Int): Flow<ResultState<List<Area>>>

    suspend fun fetchPublicAreasToAdd(
        page: Int,
        perPage: Int
    ): Flow<ResultState<List<Area>>>

    fun fetchAreaDetails(areaId: Int): Flow<ResultState<Area>>
}