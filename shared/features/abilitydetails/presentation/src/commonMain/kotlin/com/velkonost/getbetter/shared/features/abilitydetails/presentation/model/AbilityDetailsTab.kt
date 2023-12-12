package com.velkonost.getbetter.shared.features.abilitydetails.presentation.model

import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.desc.Resource
import dev.icerock.moko.resources.desc.StringDesc

enum class AbilityDetailsTab(val title: StringDesc) {
    Notes(title = StringDesc.Resource(SharedR.strings.ability_detail_notes_title)),
    Motivation(title = StringDesc.Resource(SharedR.strings.ability_detail_motivation_title))
}