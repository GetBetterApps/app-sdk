package com.velkonost.getbetter.shared.features.affirmations.data.remote

object Route {
    private const val PREFIX = "affirmations"

    const val UPDATE_FAVORITE = "$PREFIX/updateFavorite"
    const val FAVORITES_LIST = "$PREFIX/favoritesList"

    const val GET_DEMO = "$PREFIX/demo"
}