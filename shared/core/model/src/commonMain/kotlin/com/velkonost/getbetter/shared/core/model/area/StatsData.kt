package com.velkonost.getbetter.shared.core.model.area

import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.desc.ResourceFormatted
import dev.icerock.moko.resources.desc.StringDesc
import kotlinx.serialization.Serializable

@Serializable
data class StatsData(
    val notesAmount: Int,
    val membersAmount: Int,
    val tasksAmount: Int
) {
    val notesAmountStr: StringDesc
        get() = StringDesc.ResourceFormatted(SharedR.strings.diary_area_notes, notesAmount)

    val membersAmountStr: StringDesc
        get() = StringDesc.ResourceFormatted(SharedR.strings.diary_area_members, membersAmount)

    val tasksAmountStr: StringDesc
        get() = StringDesc.ResourceFormatted(SharedR.strings.diary_area_tasks, tasksAmount)
}