package com.velkonost.getbetter.android.features.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.core.compose.components.PrimaryBox
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun AppSettings(
    modifier: Modifier = Modifier
) {
    SectionTitle(text = stringResource(resource = SharedR.strings.profile_app_settings_title))

    PrimaryBox {
        Column {
            MenuItem(
                title = stringResource(resource = SharedR.strings.profile_app_settings_theming),
                icon = painterResource(imageResource = SharedR.images.ic_theming),
            ) {

            }
            MenuItem(
                modifier = modifier.padding(top = 4.dp),
                title = stringResource(resource = SharedR.strings.profile_app_settings_notifications),
                icon = painterResource(imageResource = SharedR.images.ic_notifications)
            ) {

            }
        }
    }
}