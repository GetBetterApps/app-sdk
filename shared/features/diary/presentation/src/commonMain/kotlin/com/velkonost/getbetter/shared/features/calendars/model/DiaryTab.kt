package com.velkonost.getbetter.shared.features.calendars.model

import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.desc.Resource
import dev.icerock.moko.resources.desc.StringDesc

enum class DiaryTab(val title: StringDesc) {
    Notes(title = StringDesc.Resource(SharedR.strings.diary_notes_title)),
    Areas(title = StringDesc.Resource(SharedR.strings.diary_areas_title)),
    Tasks(title = StringDesc.Resource(SharedR.strings.diary_tasks_title))
}
