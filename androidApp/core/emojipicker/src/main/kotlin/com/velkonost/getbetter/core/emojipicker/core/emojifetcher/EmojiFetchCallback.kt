package com.velkonost.getbetter.core.emojipicker.core.emojifetcher

interface EmojiFetchCallback {
    fun onFetchSuccess(
        data: String,
    )

    fun onFetchFailure(
        errorMessage: String,
    )
}
