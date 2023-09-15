package com.velkonost.getbetter.core.compose.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.sp
import com.velkonost.getbetter.shared.resources.SharedR.fonts.sfpro

private val SFFontFamily = FontFamily(
    listOf(
        Font(resId = sfpro.rounded_regular.fontResourceId, weight = FontWeight.Normal),
        Font(resId = sfpro.rounded_semibold.fontResourceId, weight = FontWeight.SemiBold),
        Font(resId = sfpro.rounded_bold.fontResourceId, weight = FontWeight.Bold),
        Font(resId = sfpro.rounded_black.fontResourceId, weight = FontWeight.Black)
    )
)

/**
 * Values taken from Google Material 3 documentation
 * [Source](https://m3.material.io/styles/typography/type-scale-tokens)
 */
@Suppress("MagicNumber")
internal val AppTypography = Typography(
    // DISPLAY .....................................................................................
    displayLarge = TextStyle(
        fontFamily = SFFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 57.sp,
        lineHeight = TextUnit(1.12F, TextUnitType.Em)
    ),
    displayMedium = TextStyle(
        fontFamily = SFFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 45.sp,
        lineHeight = TextUnit(1.15F, TextUnitType.Em)
    ),
    displaySmall = TextStyle(
        fontFamily = SFFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp,
        lineHeight = TextUnit(1.22F, TextUnitType.Em)
    ),

    // HEADLINE ....................................................................................
    headlineLarge = TextStyle(
        fontFamily = SFFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp,
        lineHeight = TextUnit(1.25F, TextUnitType.Em)
    ),
    headlineMedium = TextStyle(
        fontFamily = SFFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp,
        lineHeight = TextUnit(1.285F, TextUnitType.Em)
    ),
    headlineSmall = TextStyle(
        fontFamily = SFFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        lineHeight = TextUnit(1.33F, TextUnitType.Em)
    ),

    // TITLE .......................................................................................
    titleLarge = TextStyle(
        fontFamily = SFFontFamily,
        fontWeight = FontWeight.Black,
        fontSize = 22.sp,
        lineHeight = TextUnit(1.27F, TextUnitType.Em)
    ),
    titleMedium = TextStyle(
        fontFamily = SFFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = TextUnit(1.5F, TextUnitType.Em),
        letterSpacing = TextUnit(0.009f, TextUnitType.Em)
    ),
    titleSmall = TextStyle(
        fontFamily = SFFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = TextUnit(1.42F, TextUnitType.Em),
        letterSpacing = TextUnit(0.007f, TextUnitType.Em)
    ),

    // LABEL .......................................................................................
    labelLarge = TextStyle(
        fontFamily = SFFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = TextUnit(1.42F, TextUnitType.Em),
        letterSpacing = TextUnit(0.007f, TextUnitType.Em)
    ),
    labelMedium = TextStyle(
        fontFamily = SFFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = TextUnit(1.33F, TextUnitType.Em),
        letterSpacing = TextUnit(0.03f, TextUnitType.Em)
    ),
    labelSmall = TextStyle(
        fontFamily = SFFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 11.sp,
        lineHeight = TextUnit(1.45F, TextUnitType.Em),
        letterSpacing = TextUnit(0.045f, TextUnitType.Em)
    ),

    // BODY ........................................................................................
    bodyLarge = TextStyle(
        fontFamily = SFFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = TextUnit(1.5F, TextUnitType.Em),
        letterSpacing = TextUnit(0.03f, TextUnitType.Em)
    ),
    bodyMedium = TextStyle(
        fontFamily = SFFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = TextUnit(1.42F, TextUnitType.Em),
        letterSpacing = TextUnit(0.015f, TextUnitType.Em)
    ),
    bodySmall = TextStyle(
        fontFamily = SFFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = TextUnit(1.33F, TextUnitType.Em),
        letterSpacing = TextUnit(0.033f, TextUnitType.Em)
    )
)
