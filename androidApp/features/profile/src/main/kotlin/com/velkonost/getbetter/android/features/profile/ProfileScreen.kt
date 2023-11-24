package com.velkonost.getbetter.android.features.profile

import android.annotation.SuppressLint
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.velkonost.getbetter.android.features.profile.components.AppSettings
import com.velkonost.getbetter.android.features.profile.components.HelpAndSupport
import com.velkonost.getbetter.android.features.profile.components.ProfileHeader
import com.velkonost.getbetter.android.features.profile.components.SubscriptionBox
import com.velkonost.getbetter.core.compose.components.AppButton
import com.velkonost.getbetter.core.compose.components.VersionName
import com.velkonost.getbetter.core.compose.components.experience.LevelBlock
import com.velkonost.getbetter.core.compose.composable.OnLifecycleEvent
import com.velkonost.getbetter.shared.core.model.profile.UIMode
import com.velkonost.getbetter.shared.features.profile.ProfileViewModel
import com.velkonost.getbetter.shared.features.profile.contracts.AvatarSelected
import com.velkonost.getbetter.shared.features.profile.contracts.LogoutClick
import com.velkonost.getbetter.shared.features.profile.contracts.ThemeChange
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.stringResource


@SuppressLint("Recycle")
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel
) {
    val scrollState = rememberScrollState()
    val state by viewModel.viewState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            context.contentResolver.openInputStream(uri)
                ?.use { it.buffered().readBytes() }
                ?.let {
                    viewModel.dispatch(AvatarSelected(it))
                }
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(scrollState)
            .padding(bottom = 140.dp)
    ) {

        ProfileHeader(
            userName = state.userName,
            isLoading = state.isLoading,
            avatarUrl = state.avatarUrl,
            onAvatarClick = {
                launcher.launch("image/*")
            },
            onSettingsClick = {

            }
        )

        AnimatedVisibility(visible = state.experienceData != null) {
            state.experienceData?.let {
                LevelBlock(experienceData = it)
            }
        }


        SubscriptionBox(
            subscriptionPlan = stringResource(resource = SharedR.strings.profile_sub_basic)
        ) {

        }

        AppSettings(
            selectedTheme = state.selectedTheme,
            onThemeChanged = {
                viewModel.dispatch(ThemeChange(it))
                AppCompatDelegate.setDefaultNightMode(
                    when (it) {
                        UIMode.Light -> AppCompatDelegate.MODE_NIGHT_NO
                        UIMode.Dark -> AppCompatDelegate.MODE_NIGHT_YES
                        else -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                    }
                )
//                val configs = context.resources.configuration
//                configs.uiMode = when(it) {
//                    UIMode.Light -> Configuration.UI_MODE_NIGHT_NO
//                    UIMode.Dark -> Configuration.UI_MODE_NIGHT_YES
//                    else -> UI_MODE_NIGHT_MASK
//                }
//
////                    Configuration.UI_MODE_NIGHT_UNDEFINED
//                context.resources.updateConfiguration(configs, context.resources.displayMetrics)
            }
        )

        HelpAndSupport()
        AppButton(
            modifier = modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 48.dp),
            isLoading = state.isLogoutLoading,
            labelText = stringResource(resource = SharedR.strings.profile_logout)
        ) { viewModel.dispatch(LogoutClick) }
        VersionName()
    }

    OnLifecycleEvent { _, event ->
        when (event) {
            Lifecycle.Event.ON_RESUME -> {
                viewModel.onAppear()
            }

            else -> {}
        }
    }

}