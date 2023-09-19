package com.velkonost.getbetter.shared.core.vm.resource

import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.desc.ResourceStringDesc
import kotlin.random.Random

data class Message
internal constructor(
    val id: String,
    val text: String?,
    val textResource: ResourceStringDesc?,
    val messageType: MessageType
) {
    class Builder {
        private var id: String = Random.nextLong().toString()
        private var text: String? = null
        private var textResource: ResourceStringDesc? = null
        private var messageType: MessageType = MessageType.None

        fun id(id: String) = apply { this.id = id }

        fun text(text: String?) = apply { this.text = text }

        fun text(text: ResourceStringDesc?) = apply { this.textResource = text }

        fun messageType(type: MessageType) = apply { this.messageType = type }

        fun build() = Message(id, text, textResource, messageType)
    }
}
