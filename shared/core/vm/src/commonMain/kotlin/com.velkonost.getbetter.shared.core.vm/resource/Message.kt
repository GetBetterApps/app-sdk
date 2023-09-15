package com.velkonost.getbetter.shared.core.vm.resource

import kotlin.random.Random

data class Message
internal constructor(
    val id: String,
    val text: String?,
    val messageType: MessageType
) {
    class Builder {
        private var id: String = Random.nextLong().toString()
        private var text: String? = null
        private var messageType: MessageType = MessageType.None

        fun id(id: String) = apply { this.id = id }

        fun text(text: String?) = apply { this.text = text }

        fun messageType(type: MessageType) = apply { this.messageType = type }

        fun build() = Message(id, text, messageType)
    }
}
