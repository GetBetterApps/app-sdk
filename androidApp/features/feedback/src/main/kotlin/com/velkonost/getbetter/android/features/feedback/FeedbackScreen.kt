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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.velkonost.getbetter.android.features.feedback.components.FeedbackItem
import com.velkonost.getbetter.android.features.feedback.components.FeedbackListHeader
import com.velkonost.getbetter.core.compose.components.AppButton
import com.velkonost.getbetter.core.compose.components.Loader
import com.velkonost.getbetter.shared.features.feedback.presentation.FeedbackViewModel
import com.velkonost.getbetter.shared.features.feedback.presentation.contract.NavigateBack
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun FeedbackScreen(
    modifier: Modifier = Modifier,
    viewModel: FeedbackViewModel
) {
    val state by viewModel.viewState.collectAsStateWithLifecycle()

    Box(modifier = modifier.fillMaxSize()) {
        if (state.isLoading && state.items.isEmpty()) {
            Loader(modifier = Modifier.align(Alignment.Center))
        } else {
            Column {
                FeedbackListHeader {
                    viewModel.dispatch(NavigateBack)
                }

                LazyRow(
                    contentPadding = PaddingValues(bottom = 140.dp)
                ) {
                    items(state.items, key = { it.id!! }) { item ->
                        FeedbackItem(
                            item = item,
                            onClick = {

                            }
                        )
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
                                    colorResource(resource = SharedR.colors.main_background)
                                        .copy(alpha = 0.7f),
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

                    }
                )
            }
        }

    }
}