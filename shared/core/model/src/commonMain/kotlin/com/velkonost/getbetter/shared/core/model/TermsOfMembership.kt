package com.velkonost.getbetter.shared.core.model

enum class TermsOfMembership(val responseName: String) {
    Allow(responseName = "allow"),
    LowLevel(responseName = "lowlevel"),
    AlreadyJoined(responseName = "alreadyjoined")
}
