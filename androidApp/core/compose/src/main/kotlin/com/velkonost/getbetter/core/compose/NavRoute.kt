package com.velkonost.getbetter.core.compose

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import com.velkonost.getbetter.core.compose.composable.GeneralEnterTransition
import com.velkonost.getbetter.core.compose.composable.GeneralExitTransition
import com.velkonost.getbetter.shared.core.vm.navigation.NavigationEvent
import com.velkonost.getbetter.shared.core.vm.navigation.RouteNavigator
import dev.icerock.moko.resources.ImageResource

typealias AnimatedBackStack = AnimatedContentTransitionScope<NavBackStackEntry>

interface NavRoute<T : RouteNavigator> {

    val route: String

    val menuIcon: ImageResource?
        get() = null

    @Composable
    fun Content(viewModel: T, forceHideBottomBar: MutableState<Boolean>) =
        Content(viewModel = viewModel)

    @Composable
    fun Content(viewModel: T)

    val viewModel: T
        @Composable get

    fun getArguments(): List<NamedNavArgument> = emptyList()

    fun getDeepLinks(): List<NavDeepLink> = emptyList()

    fun getEnterTransition(): (AnimatedBackStack.() -> EnterTransition?)? = GeneralEnterTransition

    fun getExitTransition(): (AnimatedBackStack.() -> ExitTransition?)? = GeneralExitTransition

    fun getPopEnterTransition(): (AnimatedBackStack.() -> EnterTransition?)? = getEnterTransition()

    fun getPopExitTransition(): (AnimatedBackStack.() -> ExitTransition?)? = getExitTransition()

    fun asComposable(
        builder: NavGraphBuilder,
        navController: NavHostController,
        forceHideBottomBar: MutableState<Boolean>
    ) =
        builder.composable(
            route = route,
            arguments = getArguments(),
            deepLinks = getDeepLinks(),
            enterTransition = getEnterTransition(),
            exitTransition = getExitTransition(),
            popEnterTransition = getPopEnterTransition(),
            popExitTransition = getPopExitTransition()
        ) {
            with(viewModel) {
                Content(this, forceHideBottomBar)

                LaunchedEffect(Unit) {
                    navigationEvent.collect { event ->
                        onNavEvent(navController, event)
                    }
                }
            }
        }

    private fun onNavEvent(controller: NavHostController, event: NavigationEvent) =
        when (event) {
            is NavigationEvent.NavigateToRoute -> handleNavToRoute(controller, event)
            is NavigationEvent.NavigateAndPopUpToRoute -> onNavAndPopUpToRoute(controller, event)
            is NavigationEvent.PopToRoute -> onPopToRoute(controller, event)
            is NavigationEvent.NavigateUp -> onNavigateUp(controller)
        }
}

fun Iterable<NavRoute<*>>.provide(
    builder: NavGraphBuilder,
    navController: NavHostController,
    forceHideBottomBar: MutableState<Boolean>
) = forEach { it.asComposable(builder, navController, forceHideBottomBar) }

fun NavRoute<*>.provide(
    builder: NavGraphBuilder,
    navController: NavHostController,
    forceHideBottomBar: MutableState<Boolean>
) = asComposable(builder, navController, forceHideBottomBar)

private fun handleNavToRoute(
    controller: NavHostController,
    event: NavigationEvent.NavigateToRoute
) {
    if (controller.currentDestination?.route == event.route) {
        return
    }

    val currentRoute = event.args.joinArgs(event.route)
    controller.navigate(currentRoute) {
        launchSingleTop = true
        restoreState = true
//        popUpTo(controller.graph.findStartDestination().id) {
//            saveState = true
//        }
    }
}

private fun onNavAndPopUpToRoute(
    controller: NavHostController,
    event: NavigationEvent.NavigateAndPopUpToRoute
) {
    if (controller.currentDestination?.route == event.route) {
        return
    }

    var currentRoute = event.route
    if (event.rootRoute) {
        currentRoute += "_root"
    }
    currentRoute = event.args.joinArgs(currentRoute)

    controller.navigate(currentRoute) {
        launchSingleTop = true
        restoreState = !event.popUpToStart

        if (event.popUpToStart) {
            popUpTo(0)
        } else {
            popUpTo(event.popUpTo) {
                inclusive = true
                saveState = true
            }
        }
    }
}

private fun onPopToRoute(controller: NavHostController, event: NavigationEvent.PopToRoute) {
    if (controller.currentDestination?.route == event.staticRoute) {
        return
    }

    controller.getBackStackEntry(event.staticRoute).arguments?.let { bundle ->
        event.args?.forEach { (key, value) ->
            bundle.putString(key, value)
        }
    }

    controller.popBackStack(event.staticRoute, false)
}

private fun onNavigateUp(controller: NavHostController) {
    controller.currentDestination?.route?.let {
        controller.popBackStack(route = it, inclusive = true)
    }
}

private fun HashMap<String, String>?.joinArgs(route: String): String {
    return this?.entries?.fold(route) { currentRoute, (key, value) ->
        currentRoute.replace("{$key}", value.ifBlank { "null" })
    } ?: route
}
