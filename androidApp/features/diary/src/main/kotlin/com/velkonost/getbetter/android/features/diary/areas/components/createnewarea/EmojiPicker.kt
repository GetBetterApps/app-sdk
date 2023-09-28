package com.velkonost.getbetter.android.features.diary.areas.components.createnewarea

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.core.compose.components.PrimaryBox
import com.velkonost.getbetter.core.compose.extensions.horizontalFadingEdge
import com.velkonost.getbetter.shared.core.model.Emoji
import dev.icerock.moko.resources.compose.painterResource

@Composable
fun ColumnScope.EmojiPicker(
    modifier: Modifier = Modifier,
    isVisible: Boolean,
    items: List<Emoji>,
    onEmojiClick: (Emoji) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    AnimatedVisibility(
        modifier = modifier.fillMaxWidth(),
        visible = isVisible,
        enter = fadeIn() + expandIn(expandFrom = Alignment.Center),
        exit = fadeOut() + shrinkOut()
    ) {
        PrimaryBox {
            LazyHorizontalGrid(
                modifier = modifier
                    .height(150.dp)
                    .horizontalFadingEdge(),
                rows = GridCells.Adaptive(40.dp)
            ) {
                items(items) {
                    Image(
                        modifier = modifier
                            .padding(8.dp)
                            .clickable(
                                interactionSource = interactionSource,
                                indication = null
                            ) {
                                onEmojiClick.invoke(it)
                            },
                        painter = painterResource(imageResource = it.icon),
                        contentDescription = null
                    )
                }
            }
        }
    }
}