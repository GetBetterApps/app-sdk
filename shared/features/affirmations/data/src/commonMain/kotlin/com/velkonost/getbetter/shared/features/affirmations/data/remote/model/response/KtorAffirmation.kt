package com.velkonost.getbetter.shared.features.affirmations.data.remote.model.response

import com.velkonost.getbetter.shared.core.model.task.Affirmation
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KtorAffirmation(
    @SerialName("id")
    val id: Int,

    @SerialName("text")
    val text: String
)

fun KtorAffirmation.asExternalModel() =
    Affirmation(
        id = id,
        text = text
    )