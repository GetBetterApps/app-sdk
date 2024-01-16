package com.velkonost.getbetter.core.compose.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.core.compose.extensions.NoRippleTheme
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PrimaryTabs(
    modifier: Modifier = Modifier,
    tabs: List<String>,
    topPadding: Int = 40,
    alpha: Float = 1f,
    widthFraction: Float = 1f,
    shadow: Float = 8f,
    pagerState: PagerState
) {
    val scope = rememberCoroutineScope()

    CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
        TabRow(
            modifier = modifier
                .alpha(alpha)
                .padding(top = topPadding.dp, start = 20.dp, end = 20.dp)
                .shadow(
                    elevation = shadow.dp,
                    shape = MaterialTheme.shapes.medium,
                )
                .fillMaxWidth(widthFraction)
                .background(
                    color = colorResource(resource = SharedR.colors.background_item),
                    shape = MaterialTheme.shapes.medium
                ),
            selectedTabIndex = pagerState.currentPage,
            divider = {
                Spacer(modifier = modifier.height(1.dp))
            },
            containerColor = Color.Transparent,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    modifier = modifier
                        .tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                    height = 0.dp,
                    color = Color.Transparent
                )
            }
        ) {
            tabs.forEachIndexed { index, tabTitle ->
                val isTabSelected = pagerState.currentPage == index
                Tab(
                    modifier = modifier
                        .padding(4.dp)
                        .height(30.dp)
                        .background(
                            color = colorResource(
                                resource =
                                if (isTabSelected) SharedR.colors.main_background
                                else SharedR.colors.background_item
                            ),
                            shape = MaterialTheme.shapes.medium
                        )
                        .clip(shape = MaterialTheme.shapes.medium),
                    interactionSource = remember { MutableInteractionSource() },
                    selectedContentColor = Color.Transparent,
                    selected = pagerState.currentPage == index,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = {
                        Text(
                            text = tabTitle,
                            textAlign = TextAlign.Center,
                            color = colorResource(
                                resource =
                                if (pagerState.currentPage == index) SharedR.colors.icon_active
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

