package com.velkonost.getbetter.shared.features.areas.data.remote

object Route {
    private const val PREFIX = "areas"

    const val EDIT_AREA = "$PREFIX/edit"
    const val LEAVE_AREA = "$PREFIX/leave"
    const val DELETE_AREA = "$PREFIX/delete"
    const val ADD_USER_AREA = "$PREFIX/join"
    const val FETCH_ALL_AREAS = "$PREFIX/all"
    const val CREATE_NEW_AREA = "$PREFIX/new"
    const val FETCH_USER_AREAS = "$PREFIX/userAreas"
    const val FETCH_AREA_DETAILS = "$PREFIX/details"
    const val FETCH_PUBLIC_AREAS_TO_ADD = "$PREFIX/publicAreasToAdd"
}