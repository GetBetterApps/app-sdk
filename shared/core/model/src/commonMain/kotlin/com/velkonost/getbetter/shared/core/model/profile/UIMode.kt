package com.velkonost.getbetter.shared.core.model.profile

import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.desc.Resource
import dev.icerock.moko.resources.desc.StringDesc

enum class UIMode(val text: StringDesc) {
    Dark(StringDesc.Resource(SharedR.strings.uimode_dark_title)),
    Light(StringDesc.Resource(SharedR.strings.uimode_light_title)),
    System(StringDesc.Resource(SharedR.strings.uimode_system_title))
}

