package com.velkonost.getbetter.shared.core.vm.resource

import dev.icerock.moko.resources.desc.StringDesc

sealed class MessageType {

    class Sheet(
        val title: StringDesc? = null
    ) : MessageType() {
        class Builder {
            private var title: StringDesc? = null

            fun addTitle(title: StringDesc?) = apply { this.title = title }

            fun build() = Sheet(title)
        }
    }

    class SnackBar(
        val actionLabel: String?,
        val onAction: () -> Unit,
        val onDismiss: () -> Unit
    ) : MessageType() {
        class Builder {
            private var actionLabel: String? = null
            private var onAction: () -> Unit = {}
            private var onDismiss: () -> Unit = {}

            fun actionLabel(label: String?) = apply { this.actionLabel = label }

            fun onAction(block: () -> Unit) = apply { this.onAction = block }

            fun onDismiss(block: () -> Unit) = apply { this.onDismiss = block }

            fun build() = SnackBar(actionLabel, onAction, onDismiss)
        }
    }

    data object Toast : MessageType()

    data object None : MessageType()
}
