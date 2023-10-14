@file:Suppress("MagicNumber")

package com.velkonost.getbetter.core.compose.theme

import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

//val AppColorScheme
//    @Composable get() =
//        ColorScheme(
//        primary = Color(0xFFFFFFFF)
////        primary = colorResource(resource = SharedR.colors.onSurface),
//
//    )
val PrimaryColor = Color(0xff04294b)
val DarkPrimaryColor = Color(0xFFB81D24)

val Surface = Color(0xFFF8F8F8)
val DarkSurface = Color(0xFF000000)

val TextPrimary = Color(0xFF212121)
val DarkTextPrimary = Color(0xFFF8F8F8)

val TextSecondary = Color(0xFF666666)
val DarkTextSecondary = Color(0xFFC3C3C3)

val White = Color(0xFFFFFFFF)
val Black = Color(0xFF000000)
val Gray = Color(0xFF909090)
val Golden = Color(0xFFFFCA28)

val GreenColor = Color(0xff21bf72)

val NavBarColor = Color(0x80181d49)
val SettingsArrowTintColor = Color(0xff0496fd)

val LightColorPalette = lightColorScheme(
    primary = PrimaryColor,
    secondary = PrimaryColor,
    surface = Surface,
    onSurface = TextPrimary
)
