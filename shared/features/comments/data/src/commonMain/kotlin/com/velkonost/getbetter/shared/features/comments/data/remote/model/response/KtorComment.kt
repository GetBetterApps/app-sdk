package com.velkonost.getbetter.shared.features.comments.data.remote.model.response

import com.velkonost.getbetter.shared.core.model.comments.Comment
import com.velkonost.getbetter.shared.features.userinfo.data.remote.model.response.KtorUserInfoShort
import com.velkonost.getbetter.shared.features.userinfo.data.remote.model.response.asExternalModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KtorComment(
    @SerialName("text")
    val text: String,

    @SerialName("createdDate")
    val createdDate: Long,

    @SerialName("allowEdit")
    var allowEdit: Boolean = false,

    @SerialName("author")
    var author: KtorUserInfoShort
)

@Serializable
data class KtorCommentsList(
    @SerialName("items")
    val items: List<KtorComment> = emptyList()
)

fun KtorComment.asExternalModel() =
    Comment(
        text = text,
        allowEdit = allowEdit,
        createdDate = createdDate,
        author = author.asExternalModel()
    )

fun KtorCommentsList.asExternalModel() =
    items.map { it.asExternalModel() }