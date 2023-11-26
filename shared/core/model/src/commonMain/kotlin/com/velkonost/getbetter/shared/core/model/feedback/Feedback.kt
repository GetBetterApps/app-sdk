package com.velkonost.getbetter.shared.core.model.feedback

import com.velkonost.getbetter.shared.core.util.DatetimeFormatter.convertToLocalDatetime
import dev.icerock.moko.resources.desc.StringDesc

data class Feedback(
    val id: Int?,
    val type: FeedbackType,
    val status: FeedbackStatus,
    val datetime: Long?,
    val messages: List<FeedbackMessage>

)

data class FeedbackMessage(
    val fromUser: Boolean,
    val text: String,
    val datetime: Long
) {
    val datetimeStr: StringDesc
        get() = datetime.convertToLocalDatetime()
}