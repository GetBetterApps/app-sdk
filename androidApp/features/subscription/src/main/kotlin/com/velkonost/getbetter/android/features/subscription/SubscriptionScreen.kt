package com.velkonost.getbetter.android.features.subscription

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.velkonost.getbetter.android.features.subscription.components.OffersSheet
import com.velkonost.getbetter.core.compose.components.AppAlertDialog
import com.velkonost.getbetter.core.compose.components.AppButton
import com.velkonost.getbetter.core.compose.components.WeightedSpacer
import com.velkonost.getbetter.core.compose.components.webview.AppWebView
import com.velkonost.getbetter.shared.features.subscription.presentation.SubscriptionViewModel
import com.velkonost.getbetter.shared.features.subscription.presentation.contract.SubscriptionAction
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SubscriptionScreen(
    modifier: Modifier = Modifier,
    viewModel: SubscriptionViewModel
) {

    val state by viewModel.viewState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    val cancelAutoRenewDialogVisible = remember { mutableStateOf(false) }

    val firstPointVisible = remember { mutableStateOf(false) }
    val secondPointVisible = remember { mutableStateOf(false) }
    val thirdPointVisible = remember { mutableStateOf(false) }
    val forthPointVisible = remember { mutableStateOf(false) }
    val fifthPointVisible = remember { mutableStateOf(false) }
    val sixthPointVisible = remember { mutableStateOf(false) }

    val logoVisible = remember { mutableStateOf(false) }
    val titleVisible = remember { mutableStateOf(false) }
    val buttonVisible = remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()

    val offersSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true,
    )

    val webViewSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true,
    )

    LaunchedEffect(Unit) {
        scope.launch {
            delay(100)
            logoVisible.value = true
            delay(100)
            titleVisible.value = true

            delay(100)
            firstPointVisible.value = true
            delay(100)
            secondPointVisible.value = true
            delay(100)
            thirdPointVisible.value = true
            delay(100)
            forthPointVisible.value = true
            delay(100)
            fifthPointVisible.value = true
            delay(100)
            sixthPointVisible.value = true
            delay(100)
            buttonVisible.value = true
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = colorResource(resource = SharedR.colors.main_background))
    ) {
        Column(
            modifier = modifier
                .verticalScroll(rememberScrollState())
                .padding(top = 50.dp)
                .padding(start = 16.dp, end = 16.dp)
        ) {
            Row(modifier = modifier.fillMaxWidth()) {
                Image(
                    modifier = modifier
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = {
                                viewModel.dispatch(SubscriptionAction.NavigateBack)
                            }
                        ),
                    painter = painterResource(imageResource = SharedR.images.ic_close),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(
                        color = colorResource(resource = SharedR.colors.icon_active)
                    )
                )

                WeightedSpacer()

//                Text(
//                    text = stringResource(resource = SharedR.strings.paywall_restore),
//                    style = MaterialTheme.typography.titleSmall,
//                    color = colorResource(resource = SharedR.colors.text_secondary)
//                )
            }

            AnimatedVisibility(visible = logoVisible.value, label = "") {
                Image(
                    modifier = modifier
                        .fillMaxWidth()
                        .alpha(0.8f)
                        .padding(top = 16.dp),
                    painter = painterResource(
                        imageResource = if (isSystemInDarkTheme()) SharedR.images.ic_getbetter_light_
                        else SharedR.images.ic_getbetter_dark_
                    ),
                    contentDescription = null
                )
            }

            AnimatedVisibility(visible = titleVisible.value, label = "") {
                Row(modifier = modifier.padding(top = 16.dp)) {
                    Spacer(modifier.weight(1f))
                    Text(
                        text = stringResource(resource = SharedR.strings.paywall_title),
                        color = colorResource(resource = SharedR.colors.text_title),
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Spacer(modifier.weight(1f))
                }
            }

            Spacer(modifier.padding(top = 32.dp))

            AnimatedVisibility(visible = firstPointVisible.value, label = "") {
                SubscriptionPoint(
                    title = stringResource(resource = SharedR.strings.paywall_point_1),
                    visible = firstPointVisible.value,
                    index = 0
                )
            }

            AnimatedVisibility(visible = secondPointVisible.value, label = "") {
                SubscriptionPoint(
                    title = stringResource(resource = SharedR.strings.paywall_point_2),
                    visible = secondPointVisible.value,
                    index = 1
                )
            }

            AnimatedVisibility(visible = thirdPointVisible.value, label = "") {
                SubscriptionPoint(
                    title = stringResource(resource = SharedR.strings.paywall_point_3),
                    visible = thirdPointVisible.value,
                    index = 2
                )
            }

            AnimatedVisibility(visible = forthPointVisible.value, label = "") {
                SubscriptionPoint(
                    title = stringResource(resource = SharedR.strings.paywall_point_4),
                    visible = forthPointVisible.value,
                    index = 3
                )
            }

            AnimatedVisibility(visible = fifthPointVisible.value, label = "") {
                SubscriptionPoint(
                    title = stringResource(resource = SharedR.strings.paywall_point_5),
                    visible = fifthPointVisible.value,
                    index = 4
                )
            }

            AnimatedVisibility(visible = sixthPointVisible.value, label = "") {
                SubscriptionPoint(
                    title = stringResource(resource = SharedR.strings.paywall_point_6),
                    visible = sixthPointVisible.value,
                    index = 5
                )
            }

            if (state.subscription.cancelAutoRenewEnable || !state.subscription.isActive) {
                AnimatedVisibility(visible = buttonVisible.value, label = "") {
                    Row {
                        WeightedSpacer()
                        AppButton(
                            modifier = modifier.padding(top = 32.dp),
                            labelText = stringResource(
                                resource = if (!state.subscription.isActive) SharedR.strings.continue_btn
                                else SharedR.strings.subscription_cancel_autorenew
                            ),
                            isLoading = state.isLoading,
                            onClick = {
                                if (state.subscription.isActive) {
                                    cancelAutoRenewDialogVisible.value = true
                                } else {
                                    scope.launch {
                                        offersSheetState.show()
                                    }
                                }
                            }
                        )
                        WeightedSpacer()
                    }
                }

                if (!state.subscription.isActive) {
                    AnimatedVisibility(visible = buttonVisible.value, label = "") {
                        Text(
                            modifier = modifier.padding(top = 24.dp),
                            text = stringResource(resource = SharedR.strings.paywall_footer),
                            color = colorResource(resource = SharedR.colors.text_secondary),
                            style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

            Spacer(modifier.height(48.dp))

        }

        OffersSheet(
            items = state.items,
            selectedItem = state.selectedItem,
            modalSheetState = offersSheetState,
            itemClick = {
                viewModel.dispatch(SubscriptionAction.SubscriptionItemClick(it))
            },
            purchaseClick = {
                viewModel.dispatch(SubscriptionAction.SubscriptionPurchaseClick)
            }
        )

        AppWebView(
            link = state.paymentUrl,
            sheetState = webViewSheetState,
            sheetGesturesEnabled = false
        )

        if (cancelAutoRenewDialogVisible.value) {
            AppAlertDialog(
                title = stringResource(resource = SharedR.strings.subscription_cancel_autorenew),
                text = state.subscription.cancelSubscriptionText.toString(context),
                confirmTitle = stringResource(resource = SharedR.strings.confirm),
                cancelTitle = stringResource(resource = SharedR.strings.cancel),
                onDismiss = {
                    cancelAutoRenewDialogVisible.value = false
                },
                onConfirmClick = {
                    viewModel.dispatch(SubscriptionAction.CancelAutoRenewalClick)
                    cancelAutoRenewDialogVisible.value = false
                }
            )

        }
    }

    LaunchedEffect(state.paymentUrl) {

        if (!state.paymentUrl.isNullOrEmpty()) {
            scope.launch {
                webViewSheetState.show()
            }
        }

        scope.launch {
            offersSheetState.hide()
        }
    }

    LaunchedEffect(webViewSheetState.currentValue) {
        if (webViewSheetState.currentValue == ModalBottomSheetValue.Hidden) {
            viewModel.dispatch(SubscriptionAction.SubscriptionPurchaseProcessEnded)
        }
    }

}

@Composable
fun SubscriptionPoint(
    modifier: Modifier = Modifier,
    title: String,
    visible: Boolean,
    index: Int
) {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(SharedR.files.anim_mark.rawResId)
    )

    val scope = rememberCoroutineScope()
    val animVisible = remember { mutableStateOf(false) }

    LaunchedEffect(visible) {
        if (visible) {
            scope.launch {
                delay(100)
                animVisible.value = true
            }
        }
    }

    Row(verticalAlignment = Alignment.CenterVertically) {
        if (animVisible.value) {
            LottieAnimation(
                modifier = modifier.size(48.dp),
                composition = composition,
                iterations = 1,
            )
        } else {
            Spacer(modifier.size(48.dp))
        }

        Text(
            text = title,
            style = MaterialTheme.typography.labelMedium,
            color = colorResource(resource = SharedR.colors.text_title),
            textAlign = TextAlign.Start,
        )

        Spacer(modifier.weight(1f))
    }
}