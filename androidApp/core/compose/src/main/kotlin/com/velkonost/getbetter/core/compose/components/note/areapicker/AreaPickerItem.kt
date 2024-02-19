package com.velkonost.getbetter.core.compose.components.note.areapicker

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.velkonost.getbetter.core.compose.theme.Dimen.DP_12
import com.velkonost.getbetter.core.compose.theme.Dimen.DP_16
import com.velkonost.getbetter.core.compose.theme.Dimen.DP_2
import com.velkonost.getbetter.core.compose.theme.Dimen.DP_64
import com.velkonost.getbetter.shared.core.model.Emoji
import com.velkonost.getbetter.shared.core.model.area.Area
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.painterResource

@Composable
fun ColumnScope.AreaPickerItem(
    modifier: Modifier = Modifier,
    area: Area
) {

    val emojiSize = remember { DP_64 }
    val viewPadding = remember { DP_16 }
    val textTopPadding = remember { DP_12 }
    val viewHorizontalMargin = remember { DP_2 }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .align(Alignment.CenterHorizontally)
            .padding(horizontal = viewHorizontalMargin)
            .background(
                color = colorResource(resource = SharedR.colors.text_field_background),
                shape = MaterialTheme.shapes.medium
            )
            .padding(viewPadding)
    ) {
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = modifier.size(emojiSize),
                painter = painterResource(imageResource = Emoji.getIconById(area.emojiId!!)),
                contentDescription = null
            )

            Text(
                modifier = modifier.padding(top = textTopPadding),
                text = area.name,
                color = colorResource(resource = SharedR.colors.text_regular_title),
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}