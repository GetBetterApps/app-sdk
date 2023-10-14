package com.velkonost.getbetter.shared.features.notes.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SubNote(
    @SerialName(textPropertyName)
    val text: String,

    @SerialName(statePropertyName)
    val state: State
) {

    enum class State {
        InProgress, Done
    }

    companion object {
        const val textPropertyName = "text"
        const val statePropertyName = "state"
    }
}