package com.velkonost.getbetter.shared.core.model.ui

import com.velkonost.getbetter.shared.core.util.randomUUID

data class TagUI(
    val id: String = randomUUID(),
    val text: String = ""
)

val String.asTag: TagUI
    get() = TagUI(text = this)

val List<String>.asTags: List<TagUI>
    get() = map { it.asTag }