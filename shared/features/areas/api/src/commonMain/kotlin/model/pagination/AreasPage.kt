package model.pagination

import com.velkonost.getbetter.shared.core.model.response.FirestorePaginationInfo
import model.Area

data class AreasPage(
    val items: List<Area>,
    val pagination: FirestorePaginationInfo
)