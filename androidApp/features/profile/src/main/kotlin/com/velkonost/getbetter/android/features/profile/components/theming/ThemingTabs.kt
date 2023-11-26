package com.velkonost.getbetter.android.features.profile.components.theming

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.core.compose.extensions.NoRippleTheme
import com.velkonost.getbetter.shared.core.model.profile.UIThemeMode
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource

@Composable
fun ThemingTabs(
    modifier: Modifier = Modifier,
    selected: UIThemeMode,
    onClick: (UIThemeMode) -> Unit
) {

    CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
        AnimatedContent(targetState = selected, label = "") {
            Row(
                modifier = modifier.padding(top = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ThemingButton(
                    text = UIThemeMode.LightTheme.text.toString(LocalContext.current),
                    selected = it == UIThemeMode.LightTheme,
                    onClick = {
                        onClick.invoke(UIThemeMode.LightTheme)
                    }
                )
                Spacer(modifier.weight(0.1f))
                Box(
                    modifier = modifier
                        .height(16.dp)
                        .width(1.dp)
                        .background(
                            color = colorResource(resource = SharedR.colors.text_primary),
                            shape = MaterialTheme.shapes.extraLarge
                        )
                )
                Spacer(modifier.weight(0.1f))
                ThemingButton(
                    text = UIThemeMode.SystemTheme.text.toString(LocalContext.current),
                    selected = it == UIThemeMode.SystemTheme,
                    onClick = {
                        onClick.invoke(UIThemeMode.SystemTheme)
                    }
                )
                Spacer(modifier.weight(0.1f))
                Box(
                    modifier = modifier
                        .height(16.dp)
                        .width(1.dp)
                        .background(
                            color = colorResource(resource = SharedR.colors.text_primary),
                            shape = MaterialTheme.shapes.extraLarge
                        )
                )
                Spacer(modifier.weight(0.1f))
                ThemingButton(
                    text = UIThemeMode.DarkTheme.text.toString(LocalContext.current),
                    selected = it == UIThemeMode.DarkTheme,
                    onClick = {
                        onClick.invoke(UIThemeMode.DarkTheme)
                    }
                )
            }
        }

    }
}

@Composable
fun RowScope.ThemingButton(
    modifier: Modifier = Modifier,
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Text(
        modifier = modifier
            .background(
                color = colorResource(
                    resource = if (selected) SharedR.colors.button_gradient_start
                    else SharedR.colors.background_item
                ),
                shape = MaterialTheme.shapes.small
            )
            .padding(vertical = 6.dp)
            .weight(1f)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            ),
        text = text,
        color = colorResource(
            resource =
            if (selected) SharedR.colors.text_light
            else SharedR.colors.icon_inactive
        ),
        style = MaterialTheme.typography.labelSmall,
        textAlign = TextAlign.Center
    )
}