package com.velkonost.getbetter.android.features.profile

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.velkonost.getbetter.android.features.profile.components.AppSettings
import com.velkonost.getbetter.android.features.profile.components.HelpAndSupport
import com.velkonost.getbetter.android.features.profile.components.OtherBlock
import com.velkonost.getbetter.android.features.profile.components.ProfileHeader
import com.velkonost.getbetter.android.features.profile.components.SubscriptionBox
import com.velkonost.getbetter.core.compose.components.AppButton
import com.velkonost.getbetter.core.compose.components.VersionName
import com.velkonost.getbetter.core.compose.components.experience.LevelBlock
import com.velkonost.getbetter.core.compose.components.webview.AppWebView
import com.velkonost.getbetter.core.compose.composable.OnLifecycleEvent
import com.velkonost.getbetter.shared.core.model.profile.UIThemeMode
import com.velkonost.getbetter.shared.features.profile.ProfileViewModel
import com.velkonost.getbetter.shared.features.profile.contracts.AvatarSelected
import com.velkonost.getbetter.shared.features.profile.contracts.ContactUsClick
import com.velkonost.getbetter.shared.features.profile.contracts.LogoutClick
import com.velkonost.getbetter.shared.features.profile.contracts.SettingsClick
import com.velkonost.getbetter.shared.features.profile.contracts.SignUpClick
import com.velkonost.getbetter.shared.features.profile.contracts.SubscriptionClick
import com.velkonost.getbetter.shared.features.profile.contracts.ThemeChange
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.stringResource
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("Recycle")
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel,
    forceHideBottomBar: MutableState<Boolean> = mutableStateOf(false)
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

    val launcherPermissions = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            // Permission Accepted: Do something
        } else {
            // Permission Denied: Do something
        }
    }

    val webViewSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true,
    )
    val scope = rememberCoroutineScope()
    val webViewLink = remember { mutableStateOf<String?>(null) }

    BackHandler {
        scope.launch {
            if (webViewSheetState.currentValue == ModalBottomSheetValue.Expanded) {
                webViewSheetState.hide()
            }
        }
    }

    LaunchedEffect(launcherPermissions) {
        if (
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
            && ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                launcherPermissions.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }


    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .verticalScroll(scrollState)
            .padding(bottom = 140.dp, top = 16.dp)
    ) {

        ProfileHeader(
            userName = state.userName,
            isLoading = state.isLoading,
            avatarUrl = state.avatarUrl,
            isAnonymous = state.isUserAnonymous,
            onAvatarClick = {
                launcher.launch("image/*")
            },
            onSettingsClick = {
                viewModel.dispatch(SettingsClick)
            },
            onSignUpClick = {
                viewModel.dispatch(SignUpClick)
            }
        )

        AnimatedVisibility(visible = state.experienceData != null) {
            state.experienceData?.let {
                LevelBlock(experienceData = it)
            }
        }


        AnimatedVisibility(visible = state.subscriptionData.available) {
            SubscriptionBox(
                subscriptionPlan = stringResource(
                    resource = if (state.subscriptionData.subscription?.isActive == true) SharedR.strings.profile_sub_premium
                    else SharedR.strings.profile_sub_basic
                ),
                buttonText = stringResource(
                    resource = if (state.subscriptionData.subscription?.isActive == true) SharedR.strings.profile_sub_more
                    else SharedR.strings.profile_sub_upgrade
                ),
                onUpgradeClick = {
                    viewModel.dispatch(SubscriptionClick)
                }
            )
        }

        AppSettings(
            selectedTheme = state.selectedTheme,
            onThemeChanged = {
                viewModel.dispatch(ThemeChange(it))
                AppCompatDelegate.setDefaultNightMode(
                    when (it) {
                        UIThemeMode.LightTheme -> AppCompatDelegate.MODE_NIGHT_NO
                        UIThemeMode.DarkTheme -> AppCompatDelegate.MODE_NIGHT_YES
                        else -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                    }
                )
            }
        )

        HelpAndSupport(
            onContactUsClick = {
                viewModel.dispatch(ContactUsClick)
            },
            onTelegramClick = {
                val link = SharedR.strings.tg_link.getString(context)
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
                context.startActivity(intent)
            }
        )

        OtherBlock(
            onPrivacyClick = {
                scope.launch {
                    webViewLink.value = state.privacyLink
                    webViewSheetState.show()
                }
            },
            onTermsClick = {
                scope.launch {
                    webViewLink.value = state.termsLink
                    webViewSheetState.show()
                }
            }
        )

        AppButton(
            modifier = modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 48.dp),
            isLoading = state.isLogoutLoading,
            labelText = stringResource(resource = SharedR.strings.profile_logout)
        ) { viewModel.dispatch(LogoutClick) }
        VersionName()
    }

    AppWebView(
        link = webViewLink.value,
        sheetState = webViewSheetState
    )

    OnLifecycleEvent { _, event ->
        when (event) {
            Lifecycle.Event.ON_RESUME -> {
                viewModel.onAppear()
            }

            else -> {}
        }
    }

    LaunchedEffect(Unit) {
        combine(
            snapshotFlow { webViewSheetState.currentValue }
        ) { sheetState ->
            val hideBottomBar = sheetState.first() != ModalBottomSheetValue.Hidden
            hideBottomBar
        }.collect {
            forceHideBottomBar.value = it
        }
    }

}