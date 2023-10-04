package com.velkonost.getbetter.shared.core.model.response

import dev.gitlive.firebase.firestore.DocumentSnapshot

data class FirestorePaginationInfo(
    val lastVisible: DocumentSnapshot? = null
)