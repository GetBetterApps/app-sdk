package com.velkonost.getbetter.android.features.diary.areas.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.core.compose.components.PrimaryBox
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.painterResource

@Composable
fun AreaItem(
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit
) {
    val haptic = LocalHapticFeedback.current

    PrimaryBox(
        modifier = modifier
            .padding(start = 20.dp, end = 20.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                onClick.invoke("")
            }
    ) {
        Row {
            Image(
                modifier = modifier.size(64.dp),
                painter = painterResource(imageResource = SharedR.images.ic_menu_profile),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = modifier
                    .padding(start = 12.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = "Healthy health",
                    color = colorResource(resource = SharedR.colors.text_title),
                    style = MaterialTheme.typography.labelLarge
                )

                Text(
                    modifier = modifier.padding(top = 4.dp),
                    text = "также у пользователя в области должны быть кастомные поля, его прогресс: добавлена ли эта область (поле users = map(userRef to exp) в области?)",
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = colorResource(resource = SharedR.colors.text_secondary),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}