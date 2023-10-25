package com.velkonost.getbetter.shared.features.areas.data.remote

object Route {
    private const val PREFIX = "areas"

    const val EDIT_AREA = "$PREFIX/editArea"
    const val LEAVE_AREA = "$PREFIX/leaveArea"
    const val DELETE_AREA = "$PREFIX/deleteArea"
    const val ADD_USER_AREA = "$PREFIX/addUserArea"
    const val FETCH_ALL_AREAS = "$PREFIX/fetchAllAreas"
    const val CREATE_NEW_AREA = "$PREFIX/createNewArea"
    const val FETCH_USER_AREAS = "$PREFIX/fetchUserAreas"
    const val FETCH_AREA_DETAILS = "$PREFIX/fetchAreaDetails"
    const val FETCH_PUBLIC_AREAS_TO_ADD = "$PREFIX/fetchPublicAreasToAdd"
}