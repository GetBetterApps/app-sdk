import com.velkonost.getbetter.shared.core.util.ResultState
import kotlinx.coroutines.flow.Flow
import model.Area

interface AreasRepository {

    fun fetchAllAreas(): Flow<ResultState<List<Area>>>
    fun editArea(): Flow<ResultState<Unit>>
    fun createNewArea(area: Area): Flow<ResultState<Unit>>
    fun deleteArea(area: Area): Flow<ResultState<Unit>>

    fun fetchUserAreas(): Flow<ResultState<List<Area>>>
    fun addUserArea(area: Area): Flow<ResultState<Unit>>
}