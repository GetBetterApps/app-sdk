package com.velkonost.getbetter.shared.core.vm

@MustBeDocumented
@Retention(AnnotationRetention.SOURCE)
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.CONSTRUCTOR,
    AnnotationTarget.TYPE,
    AnnotationTarget.TYPE_PARAMETER,
)
actual annotation class MainThread actual constructor()