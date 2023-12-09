package com.velkonost.getbetter.shared.features.follows.data.remote

object Route {
    private const val PREFIX = "follows"

    const val ADD_FOLLOW = "$PREFIX/add"
    const val REMOVE_FOLLOW = "$PREFIX/remove"
    const val GET_USER_FOLLOWS = "$PREFIX/userFollows"
    const val GET_USER_FOLLOWERS = "$PREFIX/userFollowers"
}