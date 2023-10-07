import com.velkonost.getbetter.shared.core.util.ResultState
import com.velkonost.getbetter.shared.core.util.flowRequest
import com.velkonost.getbetter.shared.core.util.randomUUID
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.Direction
import dev.gitlive.firebase.firestore.DocumentReference
import dev.gitlive.firebase.firestore.DocumentSnapshot
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

    override fun editArea(
        id: String,
        name: String,
        description: String,
        emojiId: Int?,
        imageUrl: String?
    ): Flow<ResultState<Unit>> = flowRequest {
        val data = hashMapOf(
            Area.namePropertyName to name,
            Area.descriptionPropertyName to description,
            Area.emojiIdPropertyName to emojiId,
            Area.imageUrlPropertyName to imageUrl
        )

        db.collection("areas")
            .document(id)
            .set(data, merge = true)
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


    override fun deleteArea(areaId: String): Flow<ResultState<Unit>> = flowRequest {
        db.collection("areas")
            .document(areaId)
            .delete()
    }

    override fun leaveArea(areaId: String): Flow<ResultState<Unit>> {
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
                delay(3.seconds)
            }
        }.onFailure {
            emit(ResultState.Failure(it))
        }
    }


    override fun addUserArea(areaId: String): Flow<ResultState<String>> = flowRequest {
        val userId = Firebase.auth.currentUser?.uid

        if (userId != null) {
            val userRef = db.collection("users").document(userId)
            val area = db.collection("areas").document(areaId).get()

            val areaMembers: List<DocumentReference> =
                area.get<List<DocumentReference>>(Area.membersListPropertyName).plus(userRef)
            val usersData = area.get<Map<String, Float>>(Area.usersDataPropertyName).toMutableMap()

            if (!usersData.containsKey(userId)) {
                usersData[userId] = 0f
            }

            val data = hashMapOf(
                Area.membersListPropertyName to areaMembers,
                Area.usersDataPropertyName to usersData
            )

            db.collection("areas")
                .document(areaId)
                .set(data, merge = true)

            return@flowRequest areaId
        }

        throw Exception()
    }

    override suspend fun fetchPublicAreasToAdd(
        perPage: Int, lastElement: DocumentSnapshot?
    ): ResultState<AreasPage> =
        kotlin.runCatching {
            val userId = Firebase.auth.currentUser?.uid

            var dataRequest = db.collection("areas")
                .where(Area.isActivePropertyName, true)
                .where(Area.isPrivatePropertyName, false)
                .orderBy(Area.createdDatePropertyName, direction = Direction.DESCENDING)
                .limit(perPage)

            lastElement?.let {
                dataRequest = dataRequest.startAfter(it)
            }

            val data = dataRequest.get()

            val lastVisible = data.documents.last()
            val userAreasIds = mutableListOf<String>()

            val areas = data.documents.map { areaDocument ->
                val model = areaDocument.toAreaModel()

                if (model.membersList.any { it.userId == userId }) {
                    userAreasIds.add(model.id)
                }

                model
            }

            ResultState.Success(
                AreasPage(
                    items = areas,
                    lastElement = lastVisible
                )
            )
        }.onFailure {
            ResultState.Failure(it)
        }.getOrElse {
            ResultState.Failure(it)
        }

    override fun fetchAreaDetails(areaId: String): Flow<ResultState<Area>> = flowRequest {
        val data = db.collection("areas").document(areaId).get()

        val areaData = data.toAreaModel()

        areaData
    }
}