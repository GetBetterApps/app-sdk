package com.velkonost.getbetter.shared.features.diary.di

import com.velkonost.getbetter.shared.features.diary.DiaryViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

internal actual val DiaryPresentationPlatformModule = module {
    viewModelOf(::DiaryViewModel)
}