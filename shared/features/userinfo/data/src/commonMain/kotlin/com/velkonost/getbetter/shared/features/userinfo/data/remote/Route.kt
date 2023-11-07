package com.velkonost.getbetter.shared.features.userinfo.data.remote

object Route {
    private const val PREFIX = "userinfo"

    const val INIT_SETTINGS = "$PREFIX/initSettings"
    const val UPDATE_NAME = "$PREFIX/updateName"
    const val UPDATE_LOCALE = "$PREFIX/updateLocale"
    const val UPDATE_LAST_LOGIN = "$PREFIX/updateLastLogin"
    const val UPDATE_REGISTRATION_TIME = "$PREFIX/updateRegistrationTime"
    const val FETCH_INFO = "$PREFIX/fetchInfo"
    const val FETCH_SHORT_INFO = "$PREFIX/fetchShortInfo"
    const val LOGOUT = "$PREFIX/logout"

    const val UPLOAD_AVATAR = "$PREFIX/uploadAvatar"
}