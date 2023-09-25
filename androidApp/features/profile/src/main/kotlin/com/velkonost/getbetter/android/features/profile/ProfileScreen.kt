package com.velkonost.getbetter.android.features.profile

import android.annotation.SuppressLint
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.velkonost.getbetter.core.utils.storage.StorageDelegate
import com.velkonost.getbetter.shared.features.profile.ProfileViewModel
import com.velkonost.getbetter.shared.features.profile.contracts.AvatarUploaded
import com.velkonost.getbetter.shared.features.profile.contracts.LogoutClick
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.stringResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@SuppressLint("Recycle")
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel
) {
    val scrollState = rememberScrollState()
    val state by viewModel.viewState.collectAsStateWithLifecycle()
    val scope = rememberCoroutineScope()

    val uploadAvatarState = remember { mutableStateOf<StorageDelegate.UploadState?>(null) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            scope.launch(Dispatchers.IO) {
                StorageDelegate().uploadAvatar(it)
                    .collect { result ->
                        uploadAvatarState.value = result

                        if (result is StorageDelegate.UploadState.Success) {
                            viewModel.dispatch(AvatarUploaded(result.fileUrl))
                        }
                    }
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
            isLoading = uploadAvatarState.value is StorageDelegate.UploadState.Loading,
            avatarUrl = state.avatarUrl,
            onAvatarClick = {
                launcher.launch("image/*")
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