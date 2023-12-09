package com.velkonost.getbetter.shared.features.tasks.data.remote

object Route {
    private const val PREFIX = "tasks"

    const val COMPLETED_LIST = "$PREFIX/completedList"
    const val GET_CURRENT_LIST = "$PREFIX/currentList"

    const val ADD_TO_FAVORITE = "$PREFIX/addToFavorite"
    const val REMOVE_FROM_FAVORITE = "$PREFIX/removeFromFavorite"

    const val ADD_TO_NOT_INTERESTING = "$PREFIX/addToNotInteresting"
    const val REMOVE_FROM_NOT_INTERESTING = "$PREFIX/removeFromNotInteresting"

    const val ADD_TO_COMPLETED = "$PREFIX/addToCompleted"
    const val REMOTE_FROM_COMPLETED = "$PREFIX/removeFromCompleted"

    const val GET_DETAILS = "$PREFIX/details"
}