package com.velkonost.getbetter.android.features.diary.areas.components

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.velkonost.getbetter.core.compose.components.ExpandableButtonItem
import com.velkonost.getbetter.core.compose.components.ExpandableButtonPanel
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun BoxScope.AddAreaItem(
    modifier: Modifier = Modifier,
) {
    ExpandableButtonPanel(
        modifier = modifier,
        primaryItem = ExpandableButtonItem(
            title = stringResource(resource = SharedR.strings.profile_logout),
            icon = painterResource(imageResource = SharedR.images.ic_menu_profile),
        ),
        items = listOf(
            ExpandableButtonItem(
                title = stringResource(resource = SharedR.strings.profile_title),
                icon = painterResource(imageResource = SharedR.images.ic_chat_cloud)
            ) {

            },
            ExpandableButtonItem(
                title = stringResource(resource = SharedR.strings.profile_title),
                icon = painterResource(imageResource = SharedR.images.ic_chat_cloud)
            ) {

            }
        ),
    )
}

