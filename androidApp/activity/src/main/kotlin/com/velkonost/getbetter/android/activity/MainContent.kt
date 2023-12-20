package com.velkonost.getbetter.android.activity

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.velkonost.getbetter.android.activity.components.BottomBar
import com.velkonost.getbetter.android.activity.components.HintSheet
import com.velkonost.getbetter.android.activity.components.MainSnackBarHost
import com.velkonost.getbetter.android.activity.components.bottomBarEnter
import com.velkonost.getbetter.android.activity.components.bottomBarExit
import com.velkonost.getbetter.android.activity.components.rememberSnackBarHostState
import com.velkonost.getbetter.android.activity.di.AppScreens
import com.velkonost.getbetter.android.activity.di.addAbilitiesRoute
import com.velkonost.getbetter.android.activity.di.addCalendarRoute
import com.velkonost.getbetter.android.activity.di.addDiaryRoute
import com.velkonost.getbetter.android.activity.di.addProfileRoute
import com.velkonost.getbetter.android.activity.di.addSocialRoute
import com.velkonost.getbetter.android.activity.di.addSplashRoute
import com.velkonost.getbetter.core.compose.provide
import com.velkonost.getbetter.core.compose.theme.ApplicationTheme
import com.velkonost.getbetter.shared.core.vm.navigation.NavigationScreen
import com.velkonost.getbetter.shared.core.vm.resource.MessageDeque
import com.velkonost.getbetter.shared.core.vm.resource.MessageType
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
internal fun MainContent() {
    val context = LocalContext.current
    val navController = rememberAnimatedNavController()
    val snackBarHostState = rememberSnackBarHostState()

    val forceHideBottomBar = remember { mutableStateOf(false) }
    val hintSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true,
    )
    val hintSheet = remember { mutableStateOf<MessageType.Sheet?>(null) }

    ApplicationTheme {
        Scaffold(
            snackbarHost = { MainSnackBarHost(snackBarHostState) },
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                BottomBar(navController, forceHideBottomBar)
            },
            containerColor = colorResource(resource = SharedR.colors.main_background)
        ) {
            AnimatedNavHost(
                navController = navController,
                startDestination = "${NavigationScreen.SplashNavScreen.route}_root",
                enterTransition = { bottomBarEnter() },
                exitTransition = { bottomBarExit() }
            ) {
                AppScreens.provide(this@AnimatedNavHost, navController, forceHideBottomBar)
                addSplashRoute(navController, forceHideBottomBar)
                addSocialRoute(navController, forceHideBottomBar)
                addDiaryRoute(navController, forceHideBottomBar)
                addCalendarRoute(navController, forceHideBottomBar)
                addAbilitiesRoute(navController, forceHideBottomBar)
                addProfileRoute(navController, forceHideBottomBar)
            }
        }
        HintSheet(
            state = hintSheet.value,
            modalSheetState = hintSheetState
        )
    }

    LaunchedEffect(Unit) {
        MessageDeque().collectLatest {
            onMessageReceived(it, hintSheet, hintSheetState, snackBarHostState, context)
        }
    }
}
