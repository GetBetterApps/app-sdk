import com.velkonost.getbetter.shared.core.util.ResultState
import kotlinx.coroutines.flow.Flow
import model.Area

interface AreasRepository {

    fun fetchAllAreas(): Flow<ResultState<List<Area>>>

    fun editArea(): Flow<ResultState<Unit>>

    fun createNewArea(
        name: String,
        description: String,
        isPrivate: Boolean,
        requiredLevel: Int,
        emojiId: Int? = null,
        imageUrl: String? = null
    ): Flow<ResultState<Unit>>

    fun deleteArea(area: Area): Flow<ResultState<Unit>>

    fun fetchUserAreas(): Flow<ResultState<List<Area>>>

    fun addUserArea(area: Area): Flow<ResultState<Unit>>
}