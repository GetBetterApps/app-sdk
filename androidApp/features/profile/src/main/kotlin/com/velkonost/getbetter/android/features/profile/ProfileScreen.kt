package com.velkonost.getbetter.android.features.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.android.features.profile.components.AppSettings
import com.velkonost.getbetter.android.features.profile.components.HelpAndSupport
import com.velkonost.getbetter.android.features.profile.components.ProfileHeader
import com.velkonost.getbetter.android.features.profile.components.SubscriptionBox
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

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
    }

}

@Preview(
    showBackground = true
)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}