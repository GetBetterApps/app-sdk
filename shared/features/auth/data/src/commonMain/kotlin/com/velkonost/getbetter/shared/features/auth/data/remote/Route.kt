package com.velkonost.getbetter.shared.features.auth.data.remote

object Route {
    private const val PREFIX = "auth"

    const val REGISTER_EMAIL = "$PREFIX/registerEmail"
    const val REGISTER_ANONYMOUSLY = "$PREFIX/registerAnonymously"
    const val LOGIN_EMAIL = "$PREFIX/loginEmail"
}