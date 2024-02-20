package com.velkonost.getbetter.core.compose.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.core.compose.theme.Dimen.DP_140
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource

@Composable
fun BoxScope.ExpandableButtonPanel(
    modifier: Modifier = Modifier,
    expandable: Boolean = true,
    primaryItem: ExpandableButtonItem,
    items: List<ExpandableButtonItem>,
    paddingBottom: Dp = DP_140
) {
    val haptic = LocalHapticFeedback.current
    val interactionSource = remember { MutableInteractionSource() }

    var isExpanded by remember { mutableStateOf(false) }

    val fabSize = 64.dp
    val expandedFabWidth by animateDpAsState(
        targetValue = if (isExpanded) 170.dp else fabSize,
        animationSpec = spring(dampingRatio = 3f), label = ""
    )
    val expandedFabHeight by animateDpAsState(
        targetValue = if (isExpanded) 190.dp else fabSize,
        animationSpec = spring(dampingRatio = 3f), label = ""
    )

    val cornerRadius by animateDpAsState(
        targetValue = if (isExpanded) 12.dp else 99.dp,
        animationSpec = spring(dampingRatio = 3f), label = ""
    )

    FloatingActionButton(
        onClick = {
            haptic.performHapticFeedback(HapticFeedbackType.LongPress)
            if (expandable) {
                isExpanded = !isExpanded
            }
        },
        modifier = modifier
            .align(Alignment.BottomEnd)
            .padding(bottom = paddingBottom, end = 12.dp)
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(cornerRadius.value),
            )
            .width(expandedFabWidth)
            .height(expandedFabHeight),
        shape = RoundedCornerShape(cornerRadius.value),
        containerColor = colorResource(resource = SharedR.colors.background_icon)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            AnimatedVisibility(visible = isExpanded) {
                Column {
                    items.forEach { item ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.clickable(
                                interactionSource = interactionSource,
                                indication = null
                            ) {
                                haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                                item.onClick?.invoke()
                                if (expandable) {
                                    isExpanded = !isExpanded
                                }
                            }
                        ) {
                            Image(
                                modifier = Modifier
                                    .size(64.dp)
                                    .padding(16.dp),
                                painter = item.icon,
                                contentDescription = null,
                                colorFilter = ColorFilter.tint(
                                    color = colorResource(resource = SharedR.colors.icon_inactive)
                                )
                            )
                            Text(
                                text = item.title,
                                color = colorResource(resource = SharedR.colors.text_secondary),
                                style = MaterialTheme.typography.titleSmall,
                            )
                        }
                    }
                }
            }

            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter =
                    if (isExpanded && primaryItem.iconExpanded != null) primaryItem.iconExpanded
                    else primaryItem.icon,
                    contentDescription = null,
                    modifier = Modifier
                        .size(64.dp)
                        .padding(16.dp),
                    colorFilter = ColorFilter.tint(
                        color = colorResource(resource = SharedR.colors.icon_inactive)
                    )
                )

                AnimatedVisibility(visible = isExpanded) {
                    Text(
                        text = primaryItem.title,
                        softWrap = false,
                        color = colorResource(resource = SharedR.colors.text_secondary),
                        style = MaterialTheme.typography.titleSmall,
                    )
                }

            }
        }

    }
}

data class ExpandableButtonItem(
    val title: String,
    val icon: Painter,
    val iconExpanded: Painter? = null,
    val onClick: (() -> Unit)? = null
)