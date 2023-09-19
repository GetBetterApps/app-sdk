package com.velkonost.getbetter.shared.features.auth.presentation

import com.velkonost.getbetter.shared.resources.SharedR
import dev.gitlive.firebase.FirebaseException
import dev.gitlive.firebase.auth.FirebaseAuthEmailException
import dev.gitlive.firebase.auth.FirebaseAuthInvalidCredentialsException
import dev.gitlive.firebase.auth.FirebaseAuthInvalidUserException
import dev.gitlive.firebase.auth.FirebaseAuthUserCollisionException
import dev.gitlive.firebase.auth.FirebaseAuthWeakPasswordException
import dev.icerock.moko.resources.StringResource

fun Throwable?.getEmailLoginError(): StringResource =
    when (this) {
        is FirebaseAuthEmailException -> {
            SharedR.strings.auth_error_incorrect_email
        }

        is FirebaseAuthWeakPasswordException -> {
            SharedR.strings.auth_error_weak_password
        }

        is FirebaseAuthInvalidCredentialsException,
        is FirebaseAuthUserCollisionException,
        is FirebaseAuthInvalidUserException,
        is IllegalArgumentException -> {
            SharedR.strings.auth_error_incorrect_credentials
        }

        else -> {
            SharedR.strings.auth_login_anonymous_failure
        }
    }

fun Throwable?.getEmailRegisterError(): StringResource =
    when (this) {
        is FirebaseAuthEmailException -> {
            SharedR.strings.auth_error_incorrect_email
        }

        is FirebaseAuthWeakPasswordException -> {
            SharedR.strings.auth_error_weak_password
        }

        is FirebaseAuthUserCollisionException -> {
            SharedR.strings.auth_error_email_exist
        }

        is FirebaseAuthInvalidUserException -> {
            SharedR.strings.auth_error_incorrect_credentials
        }

        is FirebaseAuthInvalidCredentialsException, is IllegalArgumentException -> {
            SharedR.strings.auth_error_incorrect_credentials
        }

        else -> {
            SharedR.strings.auth_login_anonymous_failure
        }
    }