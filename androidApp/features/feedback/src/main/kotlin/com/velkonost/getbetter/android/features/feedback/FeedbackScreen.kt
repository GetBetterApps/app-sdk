package com.velkonost.getbetter.android.features.feedback

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.velkonost.getbetter.android.features.feedback.components.CreateNewFeedbackBottomSheet
import com.velkonost.getbetter.android.features.feedback.components.FeedbackDetailBottomSheet
import com.velkonost.getbetter.android.features.feedback.components.FeedbackItem
import com.velkonost.getbetter.android.features.feedback.components.FeedbackListHeader
import com.velkonost.getbetter.core.compose.components.AppButton
import com.velkonost.getbetter.core.compose.components.Loader
import com.velkonost.getbetter.core.compose.components.PlaceholderView
import com.velkonost.getbetter.shared.features.feedback.presentation.FeedbackViewModel
import com.velkonost.getbetter.shared.features.feedback.presentation.contract.FeedbackAction
import com.velkonost.getbetter.shared.features.feedback.presentation.contract.FeedbackAnswerAction
import com.velkonost.getbetter.shared.features.feedback.presentation.contract.FeedbackEvent
import com.velkonost.getbetter.shared.features.feedback.presentation.contract.NewFeedbackAction
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.stringResource
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FeedbackScreen(
    modifier: Modifier = Modifier,
    viewModel: FeedbackViewModel
) {
    val state by viewModel.viewState.collectAsStateWithLifecycle()
    val scope = rememberCoroutineScope()
    val createNewFeedbackSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true,
    )
    val feedbackDetailsSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true,
    )
    val selectedItemId = remember { mutableStateOf<Int?>(null) }

    Box(modifier = modifier.fillMaxSize()) {
        if (state.isLoading && state.items.isEmpty()) {
            Loader(modifier = Modifier.align(Alignment.Center))
        } else {
            Column {
                FeedbackListHeader {
                    viewModel.dispatch(FeedbackAction.NavigateBack)
                }

                if (state.items.isEmpty()) {
                    PlaceholderView(text = stringResource(resource = SharedR.strings.placeholder_profile_support))
                } else {
                    LazyColumn(
                        modifier = modifier.padding(horizontal = 16.dp),
                        contentPadding = PaddingValues(bottom = 140.dp)
                    ) {
                        items(state.items, key = { it.id!! }) { item ->
                            FeedbackItem(
                                item = item,
                                onClick = {
                                    selectedItemId.value = item.id
                                    viewModel.dispatch(FeedbackAction.DetailsClick(item.id!!))
                                    scope.launch {
                                        feedbackDetailsSheetState.show()
                                    }
                                }
                            )
                        }
                    }
                }
            }

            Column(
                modifier = modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = modifier.weight(1f))

                Box(
                    modifier = modifier
                        .height(30.dp)
                        .fillMaxWidth()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    colorResource(resource = SharedR.colors.main_background).copy(
                                        alpha = 0.7f
                                    ),
                                    colorResource(resource = SharedR.colors.main_background),
                                ),
                            ),
                        )
                )

                AppButton(
                    modifier = modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 70.dp),
                    labelText = stringResource(
                        resource = SharedR.strings.diary_areas_create_button
                    ),
                    isLoading = false,
                    onClick = {
                        scope.launch {
                            createNewFeedbackSheetState.show()
                        }
                    }
                )
            }
        }

    }

    CreateNewFeedbackBottomSheet(
        newFeedbackState = state.newFeedback,
        modalSheetState = createNewFeedbackSheetState,
        onTypeChanged = {
            viewModel.dispatch(NewFeedbackAction.TypeChanged(it))
        },
        onTextChanged = {
            viewModel.dispatch(NewFeedbackAction.TextChanged(it))
        },
        onCreateClick = {
            viewModel.dispatch(NewFeedbackAction.CreateClick)
        }
    )

    FeedbackDetailBottomSheet(
        modalSheetState = feedbackDetailsSheetState,
        item = state.items.firstOrNull { it.id == selectedItemId.value },
        feedbackDetailsState = state.feedbackDetailsState,
        onAnswerTextChanged = {
            viewModel.dispatch(FeedbackAnswerAction.AnswerTextChanged(it))
        },
        onAnswerSendClick = {
            viewModel.dispatch(FeedbackAnswerAction.SendAnswerClick)
        }
    )

    LaunchedEffect(Unit) {
        viewModel.events.collectLatest {
            when (it) {
                is FeedbackEvent.NewFeedbackCreated -> {
                    scope.launch {
                        createNewFeedbackSheetState.hide()
                    }
                }
            }
        }
    }
}