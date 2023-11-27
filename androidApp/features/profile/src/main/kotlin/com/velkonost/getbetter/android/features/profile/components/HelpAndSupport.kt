package com.velkonost.getbetter.android.features.profile.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.core.compose.components.PrimaryBox
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun HelpAndSupport(
    modifier: Modifier = Modifier,
    onContactUsClick: () -> Unit,
    onTelegramClick: () -> Unit
) {
    SectionTitle(text = stringResource(resource = SharedR.strings.profile_help_title))

    PrimaryBox {
        Column {
//            MenuItem(
//                title = stringResource(resource = SharedR.strings.profile_help_rate),
//                icon = painterResource(imageResource = SharedR.images.ic_empty_star),
//            ) {
//
//            }
            MenuItem(
//                modifier = modifier.padding(top = 4.dp),
                title = stringResource(resource = SharedR.strings.profile_help_contact_support),
                icon = painterResource(imageResource = SharedR.images.ic_chat_cloud),
                onClick = onContactUsClick
            )

            MenuItem(
                modifier = modifier.padding(top = 4.dp),
                title = stringResource(resource = SharedR.strings.tg_group_title),
                icon = painterResource(imageResource = SharedR.images.ic_telegram),
                onClick = onTelegramClick
            )
        }
    }
}