package com.velkonost.getbetter.shared.features.comments.data.remote

object Route {
    private const val PREFIX = "comments"

    const val CREATE_COMMENT = "$PREFIX/create"
    const val DELETE_COMMENT = "$PREFIX/delete"
    const val FETCH_COMMENTS = "$PREFIX/list"
    const val FETCH_COMMENT_DETAILS = "$PREFIX/details"
}