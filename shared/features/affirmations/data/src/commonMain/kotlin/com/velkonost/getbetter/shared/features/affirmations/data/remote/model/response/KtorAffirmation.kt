package com.velkonost.getbetter.shared.features.affirmations.data.remote.model.response

import com.velkonost.getbetter.shared.core.model.task.Affirmation
import com.velkonost.getbetter.shared.core.network.AFFIRMATION_IMAGE_URL
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KtorAffirmation(
    @SerialName("id")
    val id: Int,

    @SerialName("text")
    val text: String,

    @SerialName("isFavorite")
    val isFavorite: Boolean,

    @SerialName("imageUrl")
    val imageUrl: String
)

fun KtorAffirmation.asExternalModel() =
    Affirmation(
        id = id,
        text = text,
        isFavorite = isFavorite,
        imageUrl = "$AFFIRMATION_IMAGE_URL$id",
    )