package com.velkonost.getbetter.android.features.areadetail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.velkonost.getbetter.core.compose.components.Loader
import com.velkonost.getbetter.core.compose.components.MultilineTextField
import com.velkonost.getbetter.core.compose.components.SingleLineTextField
import com.velkonost.getbetter.core.compose.components.area.EmojiPicker
import com.velkonost.getbetter.core.compose.components.area.SelectedEmojiImage
import com.velkonost.getbetter.shared.core.model.Emoji
import com.velkonost.getbetter.shared.features.areadetail.presentation.AreaDetailViewModel
import com.velkonost.getbetter.shared.features.areadetail.presentation.contract.AreaDetailAction
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.stringResource
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AreaDetailScreen(
    modifier: Modifier = Modifier,
    areaId: String,
    viewModel: AreaDetailViewModel = koinViewModel(),
    modalSheetState: ModalBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true,
    )
) {
    val state by viewModel.viewState.collectAsStateWithLifecycle()
    val isEmojiPickerVisible = remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }
    val confirmDeleteAreaDialog = remember { mutableStateOf(false) }

    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        sheetBackgroundColor = colorResource(resource = SharedR.colors.main_background),
        sheetContent = {
            if (state.isLoading) {
                Box(
                    modifier = modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.9f)
                        .padding(20.dp)
                ) {
                    Loader(modifier = modifier.align(Alignment.Center))
                }
            } else {
                Box(
                    modifier = modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.9f)
                ) {
                    state.item?.let { area ->

                        Column(
                            modifier = modifier
                                .padding(20.dp)
                                .verticalScroll(rememberScrollState()),
                        ) {
                            Row {
                                Spacer(modifier = modifier.weight(1f))
                                SelectedEmojiImage(
                                    selectedEmoji = area.emoji,
                                    imageSize = 96
                                ) {
                                    if (state.isEditing) {
                                        isEmojiPickerVisible.value = !isEmojiPickerVisible.value
                                    }
                                }
                                Spacer(modifier = modifier.weight(1f))
                            }

                            EmojiPicker(
                                isVisible = isEmojiPickerVisible.value,
                                items = Emoji.values().toList(),
                                onEmojiClick = {
                                    viewModel.dispatch(AreaDetailAction.EmojiChanged(it))
                                }
                            )

                            Row(
                                modifier = modifier.padding(top = 24.dp)
                            ) {
                                Spacer(modifier = modifier.weight(1f))
                                SingleLineTextField(
                                    value = area.name,
                                    placeholderText = stringResource(resource = SharedR.strings.diary_areas_create_new_name_hint),
                                    isEnabled = state.isEditing,
                                    textAlign = TextAlign.Center,
                                    textStyle = androidx.compose.material3.MaterialTheme.typography.titleLarge,
                                    paddingValues = PaddingValues(),
                                    onValueChanged = {
                                        viewModel.dispatch(AreaDetailAction.NameChanged(it))
                                    }
                                )
                                Spacer(modifier = modifier.weight(1f))
                            }

                            Row(
                                modifier = modifier
                            ) {
                                Spacer(modifier = modifier.weight(1f))
                                MultilineTextField(
                                    value = area.description,
                                    minLines = 1,
                                    placeholderText = stringResource(resource = SharedR.strings.diary_areas_create_new_description_hint),
                                    textAlign = TextAlign.Center,
                                    isEnabled = state.isEditing
                                ) {
                                    viewModel.dispatch(AreaDetailAction.DescriptionChanged(it))
                                }
                                Spacer(modifier = modifier.weight(1f))
                            }

                        }
                    }

                    Column {
                        Spacer(modifier = modifier.weight(1f))

                        Row {
                            AnimatedVisibility(
                                modifier = modifier
                                    .weight(1f),
                                visible = !state.isEditing
                            ) {
                                Text(
                                    modifier = modifier
                                        .weight(1f)
                                        .background(
                                            color = colorResource(resource = SharedR.colors.text_field_background)
                                        )
                                        .padding(top = 24.dp, bottom = 24.dp)
                                        .clickable(
                                            interactionSource = interactionSource,
                                            indication = null
                                        ) {
                                            viewModel.dispatch(AreaDetailAction.StartEdit)
                                        },
                                    text = "Edit",
                                    textAlign = TextAlign.Center,
                                    style = androidx.compose.material3.MaterialTheme.typography.titleMedium,
                                    color = colorResource(resource = SharedR.colors.text_title)
                                )
                            }

                        }

                        Row {
                            AnimatedVisibility(
                                modifier = modifier
                                    .weight(1f),
                                visible = !state.isEditing
                            ) {
                                Row {
                                    Text(
                                        modifier = modifier
                                            .weight(1f)
                                            .background(
                                                color = colorResource(resource = SharedR.colors.onboarding_background_gradient_start)
                                            )
                                            .padding(top = 24.dp, bottom = 48.dp)
                                            .clickable(
                                                interactionSource = interactionSource,
                                                indication = null
                                            ) {
                                                viewModel.dispatch(AreaDetailAction.LeaveClick)
                                            },
                                        text = "Leave",
                                        textAlign = TextAlign.Center,
                                        style = androidx.compose.material3.MaterialTheme.typography.titleMedium,
                                        color = colorResource(resource = SharedR.colors.text_light)
                                    )
                                    Text(
                                        modifier = modifier
                                            .weight(1f)
                                            .background(
                                                color = colorResource(resource = SharedR.colors.background_item)
                                            )
                                            .padding(top = 24.dp, bottom = 48.dp)
                                            .clickable(
                                                interactionSource = interactionSource,
                                                indication = null
                                            ) {
                                                confirmDeleteAreaDialog.value = true
                                            },
                                        text = "Delete",
                                        textAlign = TextAlign.Center,
                                        style = androidx.compose.material3.MaterialTheme.typography.titleMedium,
                                        color = colorResource(resource = SharedR.colors.text_title)
                                    )
                                }
                            }
                        }
                    }

                    Column {
                        Spacer(modifier = modifier.weight(1f))
                        AnimatedVisibility(
                            modifier = modifier.fillMaxWidth(),
                            visible = state.isEditing
                        ) {
                            Text(
                                modifier = modifier
                                    .weight(1f)
                                    .background(
                                        color = colorResource(resource = SharedR.colors.onboarding_background_gradient_start)
                                    )
                                    .padding(top = 24.dp, bottom = 48.dp)
                                    .clickable(
                                        interactionSource = interactionSource,
                                        indication = null
                                    ) {
                                        isEmojiPickerVisible.value = false
                                        viewModel.dispatch(AreaDetailAction.EndEdit)
                                    },
                                text = "Save",
                                textAlign = TextAlign.Center,
                                style = androidx.compose.material3.MaterialTheme.typography.titleMedium,
                                color = colorResource(resource = SharedR.colors.text_light)
                            )
                        }
                    }

                }
            }
        }

    ) {

    }

    if (confirmDeleteAreaDialog.value) {
        AlertDialog(
            title = {
                Text(text = "Are you sure?")
            },
            text = {
                Text(text = "Delete this area")
            },
            onDismissRequest = {
                confirmDeleteAreaDialog.value = false
            },
            confirmButton = {
                Button(onClick = {
                    viewModel.dispatch(AreaDetailAction.DeleteClick)
                    confirmDeleteAreaDialog.value = false
                }) {
                    Text(text = "Delete")
                }
            },
            dismissButton = {
                Button(onClick = { confirmDeleteAreaDialog.value = false }) {
                    Text(text = "Cancel")
                }
            },
        )
    }

    LaunchedEffect(areaId) {
        viewModel.dispatch(AreaDetailAction.Load(areaId))
    }
}