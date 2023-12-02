package com.velkonost.getbetter.shared.features.tasks.data.remote.model.response

import com.velkonost.getbetter.shared.core.model.task.Task
import com.velkonost.getbetter.shared.features.abilities.data.remote.model.response.KtorAbility
import com.velkonost.getbetter.shared.features.abilities.data.remote.model.response.asExternalModel
import com.velkonost.getbetter.shared.features.areas.data.remote.model.response.KtorArea
import com.velkonost.getbetter.shared.features.areas.data.remote.model.response.asExternalModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KtorTask(
    @SerialName("id") val id: Int?,

    @SerialName("area") var area: KtorArea?,

    @SerialName("name") val name: String?,

    @SerialName("whatToDo") val whatToDo: String?,

    @SerialName("why") val why: String?,

    @SerialName("recommendedTime") val recommendedTime: Int?,

    @SerialName("requiredLevel") val requiredLevel: Int?,

    @SerialName("abilities") var abilities: List<KtorAbility>,

    @SerialName("isFavorite") var isFavorite: Boolean,

    @SerialName("isNotInteresting") var isNotInteresting: Boolean,

    @SerialName("isCompleted") var isCompleted: Boolean
)

fun KtorTask.asExternalModel() = Task(
    id = id,
    area = area?.asExternalModel(),
    name = name,
    whatToDo = whatToDo,
    why = why,
    recommendedTime = recommendedTime,
    requiredLevel = requiredLevel,
    abilities = abilities.map { it.asExternalModel() },
    isFavorite = isFavorite,
    isNotInteresting = isNotInteresting,
    isCompleted = isCompleted
)