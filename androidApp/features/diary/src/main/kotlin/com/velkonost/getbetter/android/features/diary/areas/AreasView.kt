package com.velkonost.getbetter.android.features.diary.areas

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.android.features.diary.areas.components.AddAreaItem
import com.velkonost.getbetter.android.features.diary.areas.components.AreaItem

@Composable
fun AreasView(
    modifier: Modifier = Modifier
) {
    val topBottomFade = Brush.verticalGradient(0f to Color.Transparent, 0.1f to Color.Red)

    Box {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .fadingEdge(topBottomFade),
            contentPadding = PaddingValues(bottom = 140.dp)
        ) {
            items(20) {
                AreaItem {

                }
            }

        }

        AddAreaItem()
    }
}

fun Modifier.fadingEdge(brush: Brush) = this
    .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
    .drawWithContent {
        drawContent()
        drawRect(brush = brush, blendMode = BlendMode.DstIn)
    }