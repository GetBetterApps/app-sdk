package com.velkonost.getbetter.android.features.profile.components.theming

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Tab
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.core.compose.extensions.NoRippleTheme
import com.velkonost.getbetter.shared.core.model.profile.UIMode
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource

@Composable
fun ThemingTabs(
    modifier: Modifier = Modifier,
    selected: UIMode,
    tabs: List<UIMode>,
    onClick: (UIMode) -> Unit
) {

    CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            ThemingButton(
                text = UIMode.Light.text.toString(LocalContext.current),
                selected = selected == UIMode.Light
            )
            Spacer(modifier.weight(0.5f))
            Box(
                modifier = modifier
                    .height(16.dp)
                    .width(1.dp)
                    .background(
                        color = colorResource(resource = SharedR.colors.button_gradient_start),
                        shape = MaterialTheme.shapes.extraLarge
                    )
            )
            Spacer(modifier.weight(0.5f))
            ThemingButton(
                text = UIMode.System.text.toString(LocalContext.current),
                selected = selected == UIMode.System
            )
            Spacer(modifier.weight(0.5f))
            Box(
                modifier = modifier
                    .height(6.dp)
                    .width(1.dp)
                    .background(
                        color = colorResource(resource = SharedR.colors.main_background),
                        shape = MaterialTheme.shapes.extraLarge
                    )
            )
            Spacer(modifier.weight(0.5f))
            ThemingButton(
                text = UIMode.Dark.text.toString(LocalContext.current),
                selected = selected == UIMode.Dark
            )
        }

        TabRow(
            modifier = modifier
                .padding(top = 12.dp)
//                .shadow(
//                    elevation = 1.dp,
//                    shape = MaterialTheme.shapes.medium,
//                )
                .fillMaxWidth()
                .background(
                    color = colorResource(resource = SharedR.colors.background_item),
                    shape = MaterialTheme.shapes.medium
                ),
            selectedTabIndex = tabs.indexOfFirst { it == selected },
            divider = {
//                Box(
//                    modifier = modifier
//                        .height(10.dp)
//                    .background(color = colorResource(resource = SharedR.colors.main_background))
//                )
            },
            containerColor = Color.Transparent,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    modifier = modifier
                        .tabIndicatorOffset(tabPositions[tabs.indexOfFirst { it == selected }]),
                    height = 0.dp,
                    color = Color.Transparent
                )
            }
        ) {
            tabs.forEachIndexed { _, tab ->
                val isTabSelected = tab == selected
                Tab(
                    modifier = modifier
                        .padding(4.dp)
                        .height(30.dp)
                        .background(
                            color = colorResource(
                                resource =
                                if (isTabSelected) SharedR.colors.button_gradient_start
                                else SharedR.colors.background_item
                            ),
                            shape = MaterialTheme.shapes.small
                        ),
                    interactionSource = remember { MutableInteractionSource() },
                    selectedContentColor = Color.Transparent,
                    selected = isTabSelected,
                    onClick = { onClick(tab) },
                    text = {
                        Text(
                            text = tab.text.toString(LocalContext.current),
                            textAlign = TextAlign.Center,
                            color = colorResource(
                                resource =
                                if (isTabSelected) SharedR.colors.text_light
                                else SharedR.colors.icon_inactive
                            ),
                            style = MaterialTheme.typography.labelSmall
                        )
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
    selected: Boolean
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
            .weight(1f),
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