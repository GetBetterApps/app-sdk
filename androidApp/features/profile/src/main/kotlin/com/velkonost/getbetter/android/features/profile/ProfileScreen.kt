package com.velkonost.getbetter.android.features.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.velkonost.getbetter.android.features.profile.components.AppSettings
import com.velkonost.getbetter.android.features.profile.components.HelpAndSupport
import com.velkonost.getbetter.android.features.profile.components.ProfileHeader
import com.velkonost.getbetter.android.features.profile.components.SubscriptionBox
import com.velkonost.getbetter.core.compose.components.AppButton
import com.velkonost.getbetter.core.compose.components.VersionName
import com.velkonost.getbetter.shared.features.profile.ProfileViewModel
import com.velkonost.getbetter.shared.features.profile.contracts.LogoutClick
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.stringResource


@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel
) {
    val scrollState = rememberScrollState()
    val state by viewModel.viewState.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(scrollState)
            .padding(bottom = 140.dp)
    ) {

        ProfileHeader(
            userName = "velkonost",
            onAvatarClick = {

            },
            onSettingsClick = {

            }
        )

        SubscriptionBox(
            subscriptionPlan = stringResource(resource = SharedR.strings.profile_sub_basic)
        ) {

        }

        AppSettings()
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

}