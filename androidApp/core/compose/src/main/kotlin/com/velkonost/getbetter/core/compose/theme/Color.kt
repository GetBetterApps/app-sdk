@file:Suppress("MagicNumber")

package com.velkonost.getbetter.core.compose.theme

import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val PrimaryColor = Color(0xff04294b)
val Surface = Color(0xFFF8F8F8)
val TextPrimary = Color(0xFF212121)

val LightColorPalette = lightColorScheme(
    primary = PrimaryColor,
    secondary = PrimaryColor,
    surface = Surface,
    onSurface = TextPrimary
)
