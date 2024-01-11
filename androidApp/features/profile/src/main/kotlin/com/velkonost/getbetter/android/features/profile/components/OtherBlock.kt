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
fun OtherBlock(
    modifier: Modifier = Modifier,
    onPrivacyClick: () -> Unit,
    onTermsClick: () -> Unit
) {

    SectionTitle(text = stringResource(resource = SharedR.strings.profile_help_title))

    PrimaryBox {
        Column {
            MenuItem(
                title = stringResource(resource = SharedR.strings.profile_privacy_title),
                icon = painterResource(imageResource = SharedR.images.ic_privacy),
                onClick = onPrivacyClick
            )

            MenuItem(
                modifier = modifier.padding(top = 4.dp),
                title = stringResource(resource = SharedR.strings.profile_terms_title),
                icon = painterResource(imageResource = SharedR.images.ic_terms),
                onClick = onTermsClick
            )
        }
    }

}