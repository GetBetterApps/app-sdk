package com.velkonost.getbetter.shared.core.model.profile

import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.desc.Resource
import dev.icerock.moko.resources.desc.StringDesc

enum class UIThemeMode(val text: StringDesc) {
    DarkTheme(StringDesc.Resource(SharedR.strings.uimode_dark_title)),
    SystemTheme(StringDesc.Resource(SharedR.strings.uimode_system_title)),
    LightTheme(StringDesc.Resource(SharedR.strings.uimode_light_title)),
}

