package com.velkonost.getbetter.core.compose.extensions

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.borderBevel(
    width: Dp = 1.dp,
    brush: Brush = Brush
        .verticalGradient(colors = listOf(Color(0x5CDCFD5), Color(0xA081A33))),
    shape: Shape = RoundedCornerShape(2.dp)
) = this.then(
    Modifier.border(width = width, brush = brush, shape = shape)
)