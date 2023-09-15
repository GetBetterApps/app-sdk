package com.velkonost.getbetter.android.app

import android.app.Application
import com.velkonost.getbetter.shared.umbrella.PlatformSDK

internal class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initPlatformSDK()
    }
}

internal fun MainApplication.initPlatformSDK() =
    PlatformSDK.init()