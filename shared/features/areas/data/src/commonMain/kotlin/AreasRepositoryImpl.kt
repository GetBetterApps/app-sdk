import com.velkonost.getbetter.shared.core.util.ResultState
import com.velkonost.getbetter.shared.core.util.flowRequest
import dev.gitlive.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import model.Area

class AreasRepositoryImpl
constructor(private val db: FirebaseFirestore) : AreasRepository {
    override fun fetchAllAreas(): Flow<ResultState<List<Area>>> = flowRequest {
        TODO("Not yet implemented")
    }

    override fun editArea(): Flow<ResultState<Unit>> {
        TODO("Not yet implemented")
    }

    override fun createNewArea(area: Area): Flow<ResultState<Unit>> {
        TODO("Not yet implemented")
    }

    override fun deleteArea(area: Area): Flow<ResultState<Unit>> {
        TODO("Not yet implemented")
    }

    override fun fetchUserAreas(): Flow<ResultState<List<Area>>> {
        TODO("Not yet implemented")
    }

    override fun addUserArea(area: Area): Flow<ResultState<Unit>> {
        TODO("Not yet implemented")
    }
}