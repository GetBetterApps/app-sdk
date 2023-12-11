package com.velkonost.getbetter.shared.features.diary.presentation.di

import com.velkonost.getbetter.shared.features.diary.presentation.DiaryViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

internal actual val DiaryPresentationPlatformModule = module {
    viewModelOf(::DiaryViewModel)
}