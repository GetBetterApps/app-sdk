package com.velkonost.getbetter.shared.features.feedback.data.remote

object Route {
    private const val PREFIX = "feedback"

    const val CREATE = "$PREFIX/create"
    const val ADD_ANSWER = "$PREFIX/addAnswer"
    const val GET_USER_FEEDBACKS = "$PREFIX/list"
}