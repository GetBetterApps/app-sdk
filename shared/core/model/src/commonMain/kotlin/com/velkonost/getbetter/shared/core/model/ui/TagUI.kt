package com.velkonost.getbetter.shared.core.model.ui

import com.velkonost.getbetter.shared.core.util.randomUUID

data class TagUI(
    val id: String = randomUUID(),
    val text: String = ""
)