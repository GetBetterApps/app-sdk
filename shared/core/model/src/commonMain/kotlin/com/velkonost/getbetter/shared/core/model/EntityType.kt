package com.velkonost.getbetter.shared.core.model

enum class EntityType(val responseName: String) {
    Area("area"),
    Note("note"),
    Task("task"),
    Comment("comment"),

    Goal("goal"),
    SubGoal("subgoal"),
    User("user"),
    Like("like"),

    Follow("follow"),
    Follower("follower")
}