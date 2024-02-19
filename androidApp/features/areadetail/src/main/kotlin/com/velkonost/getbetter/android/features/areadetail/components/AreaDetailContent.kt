package com.velkonost.getbetter.android.features.areadetail.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.core.compose.components.HintButton
import com.velkonost.getbetter.core.compose.components.Loader
import com.velkonost.getbetter.core.compose.components.MultilineTextField
import com.velkonost.getbetter.core.compose.components.area.EmojiPicker
import com.velkonost.getbetter.core.compose.components.area.SelectedEmojiImage
import com.velkonost.getbetter.core.compose.components.experience.LevelBlock
import com.velkonost.getbetter.shared.core.model.Emoji
import com.velkonost.getbetter.shared.core.model.likes.LikeType
import com.velkonost.getbetter.shared.features.areadetail.presentation.model.AreaDetailUI
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun AreaDetailContent(
    modifier: Modifier = Modifier,
    areaData: AreaDetailUI,
    isEditing: Boolean,
    isEmojiPickerVisible: MutableState<Boolean>,
    onEmojiClick: (Emoji) -> Unit,
    onNameChanged: (String) -> Unit,
    onDescriptionChanged: (String) -> Unit,
    onLikeClick: () -> Unit,
    onHintClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    Column(
        modifier = modifier
            .padding(20.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        Box {
            Row {
                Spacer(modifier = modifier.weight(1f))
                SelectedEmojiImage(
                    selectedEmoji = areaData.emoji,
                    imageSize = 96.dp
                ) {
                    if (isEditing) {
                        isEmojiPickerVisible.value = !isEmojiPickerVisible.value
                    }
                }
                Spacer(modifier = modifier.weight(1f))
            }

            if (!areaData.isPrivate) {
                Row {

                    HintButton(
                        onClick = onHintClick
                    )

                    Spacer(modifier.weight(1f))
                    Box {
                        AnimatedContent(
                            targetState = areaData.likesData.isLikesLoading,
                            label = ""
                        ) {
                            if (!it) {
                                Column(
                                    modifier = modifier.clickable(
                                        interactionSource = interactionSource,
                                        indication = null,
                                        onClick = onLikeClick
                                    ),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center,
                                ) {
                                    Image(
                                        modifier = modifier
                                            .size(32.dp)
                                            .padding(2.dp),
                                        painter = painterResource(
                                            imageResource = if (areaData.likesData.userLike == LikeType.Positive) SharedR.images.ic_heart
                                            else SharedR.images.ic_heart_empty
                                        ),
                                        contentDescription = null,
                                        colorFilter = ColorFilter.tint(
                                            color = colorResource(
                                                resource = SharedR.colors.button_gradient_start
                                            )
                                        )
                                    )
                                    Text(
                                        text = areaData.likesData.totalLikes.toString(),
                                        color = colorResource(resource = SharedR.colors.text_primary),
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                            } else {
                                Loader(size = 32)
                            }
                        }
                    }
                }
            }
        }

        EmojiPicker(
            isVisible = isEmojiPickerVisible.value,
            items = Emoji.entries,
            onEmojiClick = onEmojiClick
        )

        LevelBlock(
            topPadding = 2.dp,
            experienceData = areaData.experienceData
        )

        Row(modifier = modifier.padding(top = 4.dp)) {
            Spacer(modifier = modifier.weight(1f))
            MultilineTextField(
                value = areaData.name,
                minLines = 1,
                placeholderText = stringResource(resource = SharedR.strings.diary_areas_create_new_name_hint),
                isEnabled = isEditing,
                textAlign = TextAlign.Center,
                paddingValues = PaddingValues(top = 8.dp),
                onValueChanged = onNameChanged
            )
            Spacer(modifier = modifier.weight(1f))
        }

        Row {
            Spacer(modifier = modifier.weight(1f))
            MultilineTextField(
                value = areaData.description,
                minLines = 1,
                placeholderText = stringResource(resource = SharedR.strings.diary_areas_create_new_description_hint),
                textAlign = TextAlign.Center,
                isEnabled = isEditing,
                paddingValues = PaddingValues(top = 8.dp),
                onValueChanged = onDescriptionChanged
            )
            Spacer(modifier = modifier.weight(1f))
        }

        AnimatedVisibility(visible = !isEditing) {
            AreaDataContent(statsData = areaData.statsData)
        }
    }
}
