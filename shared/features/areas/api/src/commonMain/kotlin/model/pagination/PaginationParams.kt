package model.pagination

import com.velkonost.getbetter.shared.core.model.response.FirestorePaginationInfo

data class PaginationParams(val perPage: Int, val pagination: FirestorePaginationInfo)