package com.velkonost.getbetter.android.features.profile.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.core.compose.components.AppButton
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun ProfileHeader(
    modifier: Modifier = Modifier,
    isLoading: Boolean = true,
    showSettings: Boolean = true,
    userName: String,
    avatarUrl: String?,
    onAvatarClick: () -> Unit,
    onSettingsClick: () -> Unit,
    isAnonymous: Boolean = false,
    onSignUpClick: (() -> Unit)? = null
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 32.dp)
            .height(128.dp)
    ) {
        Avatar(
            modifier = modifier,
            isLoading = isLoading,
            avatarUrl = avatarUrl,
            onClick = onAvatarClick
        )

        Column {
            if (showSettings && !isAnonymous) {
                Row {
                    Spacer(modifier = modifier.weight(1f))

                    SettingsButton(modifier = modifier) {
                        onSettingsClick.invoke()
                    }
                }
            }

            Spacer(modifier = modifier.weight(1f))

            Row(modifier = modifier.padding(start = 16.dp)) {
                if (!isAnonymous) {
                    Text(
                        text = userName,
                        color = colorResource(resource = SharedR.colors.text_title),
                        style = MaterialTheme.typography.titleLarge
                    )
                } else {
                    AppButton(
                        labelText = stringResource(resource = SharedR.strings.auth_signup_button),
                        isLoading = isLoading,
                        onClick = {
                            onSignUpClick?.invoke()
                        }
                    )
                }
            }
        }
    }
}