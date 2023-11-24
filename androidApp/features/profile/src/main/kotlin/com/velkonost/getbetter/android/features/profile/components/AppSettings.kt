package com.velkonost.getbetter.android.features.profile.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.velkonost.getbetter.android.features.profile.components.theming.ThemingMenuItem
import com.velkonost.getbetter.core.compose.components.PrimaryBox
import com.velkonost.getbetter.shared.features.profile.contracts.ThemeState
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun AppSettings(
    modifier: Modifier = Modifier,
    themeState: ThemeState
) {
    SectionTitle(text = stringResource(resource = SharedR.strings.profile_app_settings_title))

    PrimaryBox {
//        Column {
        ThemingMenuItem(
            tabs = themeState.variants,
            selected = themeState.selected,
            onClick = {

            }
        )
//            MenuItem(
//                modifier = modifier.padding(top = 4.dp),
//                title = stringResource(resource = SharedR.strings.profile_app_settings_notifications),
//                icon = painterResource(imageResource = SharedR.images.ic_notifications)
//            ) {
//
//            }
//        }
    }
}