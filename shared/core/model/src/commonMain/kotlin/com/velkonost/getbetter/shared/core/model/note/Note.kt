package com.velkonost.getbetter.shared.core.model.note

import com.velkonost.getbetter.shared.core.model.LikeType
import com.velkonost.getbetter.shared.core.model.area.Area
import com.velkonost.getbetter.shared.core.model.user.UserInfoShort
import com.velkonost.getbetter.shared.core.util.DatetimeFormatter.convertToLocalDatetime
import dev.icerock.moko.resources.desc.StringDesc
import kotlinx.serialization.Serializable

@Serializable
data class Note(
    val id: Int,
    val noteType: NoteType,
    val text: String,

    val authorId: String,
    val author: UserInfoShort?,

    val createdDate: Long,
    val completionDate: Long?,
    val expectedCompletionDate: Long?,
    val expectedCompletionDateExpired: Boolean,

    val mediaUrls: List<String>,
    val tags: List<String>,

    val isActive: Boolean,
    val isPrivate: Boolean,

    val subNotes: List<SubNote>,
    var area: Area,

    val allowEdit: Boolean,

    var isLikesLoading: Boolean = false,
    var totalLikes: Int,
    var userLike: LikeType
) {
    val createdDateStr: StringDesc
        get() = createdDate.convertToLocalDatetime()

    val completionDateStr: StringDesc?
        get() = completionDate?.convertToLocalDatetime()

    val expectedCompletionDateStr: StringDesc?
        get() = expectedCompletionDate?.convertToLocalDatetime()

}

fun Note.withoutAuthorData(): Note = copy(author = null)

