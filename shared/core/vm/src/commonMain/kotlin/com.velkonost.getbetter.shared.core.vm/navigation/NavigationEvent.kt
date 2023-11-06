package com.velkonost.getbetter.shared.core.vm.navigation

import kotlin.random.Random

sealed class NavigationEvent {

    data class NavigateToRoute(
        val id: String = Random.nextLong().toString(),
        val route: String,
        val args: HashMap<String, String>? = null
    ) : NavigationEvent()

    data class PopToRoute(
        val id: String = Random.nextLong().toString(),
        val staticRoute: String,
        val args: HashMap<String, String>? = null
    ) : NavigationEvent()

    data class NavigateAndPopUpToRoute(
        val id: String = Random.nextLong().toString(),
        val route: String,
        val popUpTo: String,
        val popUpToStart: Boolean = false,
        val args: HashMap<String, String>? = null,
        val rootRoute: Boolean = false
    ) : NavigationEvent()

    data class NavigateUp(
        val id: String = Random.nextLong().toString(),
        val inclusive: Boolean = false,
        val args: HashMap<String, String>? = null
    ) : NavigationEvent()
}