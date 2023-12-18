package com.velkonost.getbetter.android.features.onboarding.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.shared.core.model.task.Ability
import com.velkonost.getbetter.shared.core.util.randomUUID
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BoxScope.OnboardingThirdStep(
    modifier: Modifier = Modifier,
    enable: Boolean,
    items: List<Ability>,
    animationEnded: MutableState<Boolean>,
) {
    val animationDuration = 1200
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { items.size })

    LaunchedEffect(enable) {
        if (enable) {
            delay(1000)
            animationEnded.value = true
        }
    }

    Column(
        modifier
            .fillMaxWidth()
            .align(Alignment.Center)
    ) {
        AnimatedVisibility(
            modifier = modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            visible = enable,
            enter = fadeIn(
                tween(
                    durationMillis = animationDuration,
                    easing = LinearEasing
                )
            ),
            exit = fadeOut(
                tween(
                    durationMillis = animationDuration,
                    easing = LinearEasing
                )
            )
        ) {
            HorizontalPager(
                modifier = modifier
                    .height(250.dp)
                    .fillMaxWidth()
//                    .horizontalFadingEdge()
                    .padding(bottom = 16.dp),
                state = pagerState,
                contentPadding = PaddingValues(start = 38.dp, end = 38.dp),
                key = {
                    if (items.isNotEmpty()) {
                        items[it].id.toString()
                    } else randomUUID()
                }
            ) {
                if (items.isEmpty()) {
                    return@HorizontalPager
                }

                OnboardingAbilityItem(item = items[it])
            }
        }
    }
}