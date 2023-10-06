package model.pagination

import dev.gitlive.firebase.firestore.DocumentSnapshot
import model.Area

data class AreasPage(
    val items: List<Area>,
    val lastElement: DocumentSnapshot?
)