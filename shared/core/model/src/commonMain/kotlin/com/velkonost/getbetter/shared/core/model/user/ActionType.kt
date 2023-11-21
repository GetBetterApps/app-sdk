package com.velkonost.getbetter.shared.core.model.user

enum class ActionType(val responseName: String) {
    Add("add"),
    Remove("remove"),

    Join("join"),
    Leave("leave"),

    Complete("complete"),
    UnComplete("uncomplete"),

    AddInbox("addInbox"),

}