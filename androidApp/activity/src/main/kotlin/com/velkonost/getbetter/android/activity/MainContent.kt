package com.velkonost.getbetter.android.activity

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.velkonost.getbetter.android.activity.components.BottomBar
import com.velkonost.getbetter.android.activity.components.MainSnackBarHost
import com.velkonost.getbetter.android.activity.components.rememberSnackBarHostState
import com.velkonost.getbetter.android.activity.di.AppScreens
import com.velkonost.getbetter.core.compose.provide
import com.velkonost.getbetter.core.compose.theme.ApplicationTheme
import com.velkonost.getbetter.shared.core.vm.navigation.NavigationScreen
import com.velkonost.getbetter.shared.core.vm.resource.Message
import com.velkonost.getbetter.shared.core.vm.resource.MessageDeque
import com.velkonost.getbetter.shared.core.vm.resource.MessageType
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
internal fun MainContent() {
    val context = LocalContext.current
    val navController = rememberAnimatedNavController()
    val snackbarHostState = rememberSnackBarHostState()

    ApplicationTheme {
        Scaffold(
            snackbarHost = { MainSnackBarHost(snackbarHostState) },
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            bottomBar = {
                BottomBar(navController)
            }
        ) {
            AnimatedNavHost(
                navController = navController,
                startDestination = NavigationScreen.AuthNavScreen.route
            ) {
                AppScreens.provide(this@AnimatedNavHost, navController)
            }
        }
    }

    LaunchedEffect(Unit) {
        MessageDeque().collectLatest {
            onMessageReceived(it, snackbarHostState, context)
        }
    }
}

private suspend fun onMessageReceived(
    message: Message,
    snackbarHostState: SnackbarHostState,
    context: Context
) {
    when (val component = message.messageType) {
        is MessageType.SnackBar -> {
            val text = message.text ?: message.textResource?.toString(context = context) ?: ""
            val result = snackbarHostState.showSnackbar(
                message = text,
                actionLabel = component.actionLabel,
                withDismissAction = true,
                duration = SnackbarDuration.Short
            )

            when (result) {
                SnackbarResult.Dismissed -> {
                    component.onDismiss.invoke()
                    MessageDeque.dequeue()
                }

                SnackbarResult.ActionPerformed -> {
                    component.onAction.invoke()
                    MessageDeque.dequeue()
                }
            }

        }

        is MessageType.Toast -> {
            Toast.makeText(context, message.text, Toast.LENGTH_LONG).show()

            delay(timeMillis = 4_000)
            MessageDeque.dequeue()
        }

        else -> {
            // Ignore this block
            // MessageType could be .None
            // or message could be null
        }
    }
}