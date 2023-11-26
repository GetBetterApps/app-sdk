package com.velkonost.getbetter.android.features.profile.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.velkonost.getbetter.android.features.profile.components.theming.ThemingMenuItem
import com.velkonost.getbetter.core.compose.components.PrimaryBox
import com.velkonost.getbetter.shared.core.model.profile.UIThemeMode
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun AppSettings(
    modifier: Modifier = Modifier,
    selectedTheme: UIThemeMode,
    onThemeChanged: (UIThemeMode) -> Unit
) {
    SectionTitle(text = stringResource(resource = SharedR.strings.profile_app_settings_title))

    PrimaryBox {
//        Column {
        ThemingMenuItem(
            selected = selectedTheme,
            onClick = {
                if (it != selectedTheme) {
                    onThemeChanged.invoke(it)
                }
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