import com.velkonost.getbetter.shared.core.model.response.FirestorePaginationInfo
import com.velkonost.getbetter.shared.core.util.ResultState
import com.velkonost.getbetter.shared.core.util.flowRequest
import com.velkonost.getbetter.shared.core.util.randomUUID
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.Direction
import dev.gitlive.firebase.firestore.FirebaseFirestore
import dev.gitlive.firebase.firestore.Timestamp
import dev.gitlive.firebase.firestore.orderBy
import dev.gitlive.firebase.firestore.startAfter
import dev.gitlive.firebase.firestore.where
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import model.Area
import model.pagination.AreasPage
import model.toAreaModel
import kotlin.time.Duration.Companion.seconds

class AreasRepositoryImpl
constructor(private val db: FirebaseFirestore) : AreasRepository {

    override fun fetchAllAreas(): Flow<ResultState<List<Area>>> = flow {
        while (true) {
            val data = db.collection("areas")
                .where(Area.isActivePropertyName, true)
                .orderBy(Area.createdDatePropertyName, direction = Direction.DESCENDING)
                .get()

            val areas = data.documents.map { areaDocument ->
                areaDocument.toAreaModel()
            }

            emit(ResultState.Success(areas))
            delay(5.seconds)
        }
    }

    override fun editArea(): Flow<ResultState<Unit>> {
        TODO("Not yet implemented")
    }

    override fun createNewArea(
        name: String,
        description: String,
        isPrivate: Boolean,
        requiredLevel: Int,
        emojiId: Int?,
        imageUrl: String?
    ): Flow<ResultState<Unit>> = flowRequest {
        val userId = Firebase.auth.currentUser?.uid

        if (userId != null) {
            val areaId = randomUUID()
            val userRef = db.collection("users").document(userId)

            val data = hashMapOf(
                Area.idPropertyName to areaId,
                Area.authorPropertyName to userRef,
                Area.namePropertyName to name,
                Area.descriptionPropertyName to description,
                Area.isPrivatePropertyName to isPrivate,
                Area.requiredLevelPropertyName to requiredLevel,
                Area.emojiIdPropertyName to emojiId,
                Area.imageUrlPropertyName to imageUrl,
                Area.isActivePropertyName to true,
                Area.createdDatePropertyName to Timestamp.ServerTimestamp,
                Area.membersListPropertyName to listOf(userRef),
                Area.usersDataPropertyName to hashMapOf(userId to 0f)
            )

            db.collection("areas")
                .document(areaId)
                .set(data, merge = true)
        }

    }


    override fun deleteArea(area: Area): Flow<ResultState<Unit>> {
        TODO("Not yet implemented")
    }

    override fun fetchUserAreas(): Flow<ResultState<List<Area>>> = flow {
        emit(ResultState.Loading)

        kotlin.runCatching {
            val userId = Firebase.auth.currentUser?.uid
            val userRef = db.collection("users").document(userId!!)

            while (true) {
                val data = db.collection("areas")
                    .where(Area.isActivePropertyName, true)
                    .where(Area.membersListPropertyName, arrayContains = userRef)
                    .orderBy(Area.createdDatePropertyName, direction = Direction.DESCENDING)
                    .get()

                val areas = data.documents.map { areaDocument ->
                    areaDocument.toAreaModel()
                }

                emit(ResultState.Success(areas))
                delay(5.seconds)
            }
        }.onFailure {
            emit(ResultState.Failure(it))
        }

    }


    override fun addUserArea(area: Area): Flow<ResultState<Unit>> {
        TODO("Not yet implemented")
    }

    override suspend fun fetchPublicAreasToAdd(
        perPage: Int, pagination: FirestorePaginationInfo
    ): ResultState<AreasPage> =
        kotlin.runCatching {
            var dataRequest = db.collection("areas")
                .where(Area.isActivePropertyName, true)
                .where(Area.isPrivatePropertyName, false)
//            .where(Area.membersListPropertyName, arrayContains = userRef)
                .orderBy(Area.createdDatePropertyName, direction = Direction.DESCENDING)
                .limit(perPage)

            pagination.lastVisible?.let {
                dataRequest = dataRequest.startAfter(pagination.lastVisible!!)
            }

            val data = dataRequest.get()

            val lastVisible = data.documents.last()

            val areas = data.documents.map { areaDocument ->
                areaDocument.toAreaModel()
            }

            ResultState.Success(AreasPage(areas, FirestorePaginationInfo(lastVisible)))
        }.onFailure {
            ResultState.Failure(it)
        }.getOrElse {
            ResultState.Failure(it)
        }
}