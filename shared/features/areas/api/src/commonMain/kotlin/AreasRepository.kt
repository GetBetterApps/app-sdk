import com.velkonost.getbetter.shared.core.util.ResultState
import dev.gitlive.firebase.firestore.DocumentSnapshot
import kotlinx.coroutines.flow.Flow
import model.Area
import model.pagination.AreasPage

interface AreasRepository {

    fun fetchAllAreas(): Flow<ResultState<List<Area>>>

    fun editArea(
        id: String,
        name: String,
        description: String,
        emojiId: Int? = null,
        imageUrl: String? = null
    ): Flow<ResultState<Unit>>

    fun createNewArea(
        name: String,
        description: String,
        isPrivate: Boolean,
        requiredLevel: Int,
        emojiId: Int? = null,
        imageUrl: String? = null
    ): Flow<ResultState<Unit>>

    fun deleteArea(areaId: String): Flow<ResultState<Unit>>

    fun leaveArea(areaId: String): Flow<ResultState<Unit>>

    fun fetchUserAreas(): Flow<ResultState<List<Area>>>

    fun addUserArea(areaId: String): Flow<ResultState<String>>

    suspend fun fetchPublicAreasToAdd(
        perPage: Int, lastElement: DocumentSnapshot?
    ): ResultState<AreasPage>

    fun fetchAreaDetails(areaId: String): Flow<ResultState<Area>>
}