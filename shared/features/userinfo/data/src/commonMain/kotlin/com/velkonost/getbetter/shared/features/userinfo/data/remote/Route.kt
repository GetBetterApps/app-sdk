package com.velkonost.getbetter.shared.features.userinfo.data.remote

object Route {
    private const val PREFIX = "userinfo"

    const val INIT_SETTINGS = "$PREFIX/initSettings"
    const val UPDATE_NAME = "$PREFIX/name"
    const val UPDATE_LOCALE = "$PREFIX/locale"
    const val UPDATE_LAST_LOGIN = "$PREFIX/lastLogin"
    const val UPDATE_REGISTRATION_TIME = "$PREFIX/registrationTime"
    const val FETCH_INFO = "$PREFIX/fetchInfo"
    const val FETCH_SHORT_INFO = "$PREFIX/fetchShortInfo"
    const val LOGOUT = "$PREFIX/logout"

    const val UPLOAD_AVATAR = "$PREFIX/uploadAvatar"
    const val GET_AVATAR = "$PREFIX/getAvatar/{userId:.+}"

    const val CHANGE_PASSWORD = "$PREFIX/changePassword"
    const val DELETE_ACCOUNT = "$PREFIX/delete"

    const val BLOCK_USER = "$PREFIX/block"
}