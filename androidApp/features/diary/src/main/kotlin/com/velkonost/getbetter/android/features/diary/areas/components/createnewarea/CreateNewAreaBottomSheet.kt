package com.velkonost.getbetter.android.features.diary.areas.components.createnewarea

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.core.compose.components.AppButton
import com.velkonost.getbetter.core.compose.components.HintButton
import com.velkonost.getbetter.core.compose.components.Loader
import com.velkonost.getbetter.core.compose.components.MultilineTextField
import com.velkonost.getbetter.core.compose.components.SingleLineTextField
import com.velkonost.getbetter.core.compose.components.area.EmojiPicker
import com.velkonost.getbetter.core.compose.components.area.SelectedEmojiImage
import com.velkonost.getbetter.shared.core.model.Emoji
import com.velkonost.getbetter.shared.features.diary.presentation.contracts.CreateNewAreaViewState
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.stringResource


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CreateNewAreaBottomSheet(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    modalSheetState: ModalBottomSheetState,
    state: CreateNewAreaViewState,
    emojiItems: List<Emoji>,
    onEmojiClick: (Emoji) -> Unit,
    onNameChanged: (String) -> Unit,
    onDescriptionChanged: (String) -> Unit,
    onRequiredLevelChanged: (Int) -> Unit,
    onPrivateChanged: (Boolean) -> Unit,
    onCreateClick: () -> Unit,
    onHintClick: () -> Unit
) {
    val isEmojiPickerVisible = remember { mutableStateOf(false) }

    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        sheetBackgroundColor = colorResource(resource = SharedR.colors.main_background),
        sheetContent = {
            if (isLoading) {
                Box(
                    modifier = modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.9f)
                        .padding(20.dp)
                ) {
                    Loader(modifier = modifier.align(Alignment.Center))
                }
            } else {
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.9f)
                        .padding(20.dp)
                ) {

                    Row(
                        modifier = modifier.align(Alignment.CenterHorizontally),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(
                                resource = SharedR.strings.diary_areas_create_new_area_title
                            ),
                            color = colorResource(resource = SharedR.colors.text_title),
                            style = MaterialTheme.typography.headlineSmall
                        )
                        HintButton(
                            modifier = modifier.padding(start = 8.dp),
                            onClick = onHintClick
                        )
                    }


                    Row(modifier = modifier.padding(top = 24.dp)) {
                        SelectedEmojiImage(selectedEmoji = state.selectedEmoji) {
                            isEmojiPickerVisible.value = !isEmojiPickerVisible.value
                        }

                        SingleLineTextField(
                            value = state.name,
                            placeholderText = stringResource(
                                resource = SharedR.strings.diary_areas_create_new_name_hint
                            ),
                            onValueChanged = { onNameChanged.invoke(it) }
                        )
                    }

                    EmojiPicker(
                        isVisible = isEmojiPickerVisible.value,
                        items = emojiItems,
                        onEmojiClick = { onEmojiClick.invoke(it) }
                    )

                    MultilineTextField(
                        value = state.description,
                        placeholderText = stringResource(
                            resource = SharedR.strings.diary_areas_create_new_description_hint
                        ),
                        onValueChanged = { onDescriptionChanged.invoke(it) }
                    )

                    RequiredLevelRow(
                        title = stringResource(
                            resource = SharedR.strings.diary_areas_create_new_required_level
                        ),
                        level = state.requiredLevel,
                        onRequiredLevelChanged = { onRequiredLevelChanged.invoke(it) }
                    )

                    PrivateSwitch(
                        isPrivate = state.isPrivate,
                        onCheckedChange = onPrivateChanged
                    )

                    Spacer(modifier = modifier.weight(1f))
                    AppButton(
                        modifier = modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(bottom = 70.dp),
                        labelText = stringResource(
                            resource = SharedR.strings.diary_areas_create_button
                        ),
                        isLoading = false,
                        onClick = onCreateClick
                    )
                }
            }
        }
    ) {

    }
}




