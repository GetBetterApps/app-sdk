package com.velkonost.getbetter.shared.core.model.feedback

import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.desc.Resource
import dev.icerock.moko.resources.desc.StringDesc

enum class FeedbackType(val responseName: String, val uiContent: StringDesc) {
    Feature(
        responseName = "feature",
        uiContent = StringDesc.Resource(SharedR.strings.support_feature_title)
    ),
    Report(
        responseName = "report",
        uiContent = StringDesc.Resource(SharedR.strings.support_report_title)
    )
}