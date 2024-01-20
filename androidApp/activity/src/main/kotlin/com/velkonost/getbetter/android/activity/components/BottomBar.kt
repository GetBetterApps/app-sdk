package com.velkonost.getbetter.android.activity.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.hapticfeedback.HapticFeedback
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.velkonost.getbetter.android.activity.di.NavigationScreens
import com.velkonost.getbetter.core.compose.NavRoute
import com.velkonost.getbetter.core.compose.extensions.advancedShadow
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.painterResource

@Composable
fun BottomBar(
    navController: NavHostController,
    forceHideBottomBar: MutableState<Boolean>
) {

    val navStackBackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navStackBackEntry?.destination
    val haptic = LocalHapticFeedback.current

    val isVisible = currentDestination?.route in NavigationScreens.map { it.route }
    AnimatedVisibility(
        visible = isVisible && !forceHideBottomBar.value,
        enter = slideInVertically(
            animationSpec = tween(
                durationMillis = 500,
                delayMillis = 10,
                easing = FastOutSlowInEasing
            ),
            initialOffsetY = { it }
        ).plus(
            fadeIn(
                animationSpec = tween(
                    durationMillis = 150,
                    delayMillis = 10,
                    easing = FastOutSlowInEasing
                )
            )
        ),
        exit = slideOutVertically(
            animationSpec = tween(
                durationMillis = 500,
                delayMillis = 10,
                easing = FastOutSlowInEasing
            ),
            targetOffsetY = { it }
        )
    ) {
        Row(
            modifier = Modifier
                .advancedShadow(
                    cornersRadius = 32.dp,
                    shadowBlurRadius = 8.dp
                )
                .fillMaxWidth()
                .height(95.dp)
                .background(
                    color = colorResource(resource = SharedR.colors.background_item),
                    shape = RoundedCornerShape(
                        topStart = 32.dp,
                        topEnd = 32.dp
                    )
                ),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Top
        ) {
            NavigationScreens.forEach { screen ->
                BottomBarItem(
                    screen, currentDestination, navController, haptic
                )
            }
        }
    }

}

@Composable
fun BottomBarItem(
    screen: NavRoute<*>,
    currentDestination: NavDestination?,
    navController: NavHostController,
    haptic: HapticFeedback
) {
    val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true

    Box(
        modifier = Modifier
            .padding(top = 8.dp)
            .size(50.dp)
            .clip(shape = MaterialTheme.shapes.extraLarge)
            .clickable {
                if (navController.currentDestination?.route == screen.route) {
                    return@clickable
                }

                haptic.performHapticFeedback(HapticFeedbackType.LongPress)

                navController.navigate("${screen.route}_root") {
                    launchSingleTop = true
                    restoreState = true

                    val navigationRoutes = NavigationScreens
                        .map { it.route }
                    val firstBottomBarDestination = navController.currentBackStack.value
                        .firstOrNull { navigationRoutes.contains(it.destination.route) }
                        ?.destination

                    if (firstBottomBarDestination != null) {
                        popUpTo(firstBottomBarDestination.id) {
                            inclusive = false
                            saveState = true
                        }
                    }
                }
            }
    ) {
        Image(
            modifier = Modifier.align(Alignment.Center),
            painter = painterResource(imageResource = screen.menuIcon!!),
            contentDescription = null,
            colorFilter =
            ColorFilter.tint(
                colorResource(
                    resource =
                    if (selected) SharedR.colors.icon_active
                    else SharedR.colors.icon_inactive
                )
            )
        )
    }

}

