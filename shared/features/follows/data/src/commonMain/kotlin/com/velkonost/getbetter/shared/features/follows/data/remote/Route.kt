package com.velkonost.getbetter.shared.features.follows.data.remote

object Route {
    private const val PREFIX = "follows"

    const val ADD_FOLLOW = "$PREFIX/add"
    const val REMOVE_FOLLOW = "$PREFIX/remove"
    const val GET_USER_FOLLOWS = "$PREFIX/getUserFollows"
    const val GET_USER_FOLLOWERS = "$PREFIX/getUserFollowers"
}