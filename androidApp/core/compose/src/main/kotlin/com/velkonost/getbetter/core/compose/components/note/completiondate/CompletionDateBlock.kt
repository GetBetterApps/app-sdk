package com.velkonost.getbetter.core.compose.components.note.completiondate

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import com.velkonost.getbetter.core.compose.components.AppAlertDialog
import com.velkonost.getbetter.core.compose.components.AppDatePickerDialog
import com.velkonost.getbetter.core.compose.components.PrimaryBox
import com.velkonost.getbetter.core.compose.components.WeightedSpacer
import com.velkonost.getbetter.core.compose.theme.Dimen.DP_12
import com.velkonost.getbetter.core.compose.theme.Dimen.DP_16
import com.velkonost.getbetter.core.compose.theme.Dimen.DP_4
import com.velkonost.getbetter.core.compose.theme.Dimen.DP_60
import com.velkonost.getbetter.core.compose.theme.Pixel.PX_ZERO
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource
import dev.icerock.moko.resources.desc.Resource
import dev.icerock.moko.resources.desc.StringDesc

@Composable
fun CompletionDateBlock(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    initialValue: Long? = null,
    initialValueStr: String? = null,
    isCompleteVisible: Boolean = false,
    completionDateStr: String? = null,
    onSetCompletionDate: (Long?) -> Unit,
    onCompleteClick: (() -> Unit)? = null
) {

    val viewPadding = remember { PX_ZERO }
    val cancelIconSize = remember { DP_16 }
    val labelRowHeight = remember { DP_60 }
    val labelInnerPadding = remember { DP_12 }
    val cancelIconTrailingPadding = remember { DP_4 }
    val rowHorizontalInnerPadding = remember { DP_16 }

    val context = LocalContext.current

    val notSetText = StringDesc
        .Resource(SharedR.strings.create_note_completion_date_hint)
        .toString(context)
    var date by remember { mutableStateOf(initialValueStr ?: notSetText) }

    var showDatePicker by remember { mutableStateOf(false) }
    val confirmCancelCompletionDialog = remember { mutableStateOf(false) }

    val interactionSource = remember { MutableInteractionSource() }

    PrimaryBox(padding = viewPadding) {
        Column {
            Row(
                modifier = modifier.height(labelRowHeight),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = modifier.padding(start = rowHorizontalInnerPadding),
                    text = stringResource(resource = SharedR.strings.create_note_completion_date_title),
                    style = MaterialTheme.typography.titleMedium,
                    color = colorResource(resource = SharedR.colors.text_primary)
                )

                WeightedSpacer()

                AnimatedVisibility(visible = date != notSetText && enabled) {
                    Image(
                        modifier = modifier
                            .padding(end = cancelIconTrailingPadding)
                            .size(cancelIconSize)
                            .clickable(
                                interactionSource = interactionSource,
                                indication = null
                            ) {
                                date = notSetText
                                onSetCompletionDate.invoke(null)
                            },
                        painter = painterResource(imageResource = SharedR.images.ic_cancel),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(colorResource(resource = SharedR.colors.icon_inactive))
                    )
                }

                AnimatedContent(targetState = date, label = "") { content ->
                    Text(
                        modifier = modifier
                            .padding(end = rowHorizontalInnerPadding)
                            .background(
                                color = colorResource(resource = SharedR.colors.text_field_background),
                                shape = MaterialTheme.shapes.medium
                            )
                            .padding(labelInnerPadding)
                            .clickable(
                                interactionSource = interactionSource,
                                indication = null
                            ) {
                                if (enabled) {
                                    showDatePicker = true
                                }
                            },
                        text = content,
                        style = MaterialTheme.typography.titleMedium,
                        color = colorResource(resource = SharedR.colors.text_primary),
                    )
                }
            }

            AnimatedVisibility(visible = isCompleteVisible) {
                if (onCompleteClick != null) {
                    CompletionDateButton(
                        label = completionDateStr,
                        isLoading = isLoading,
                        onCompleteClick = onCompleteClick
                    )
                }
            }

            CompletedOnBlock(label = completionDateStr) {
                if (isCompleteVisible) {
                    confirmCancelCompletionDialog.value = true
                }
            }
        }
    }

    if (showDatePicker) {
        AppDatePickerDialog(
            title = stringResource(resource = SharedR.strings.create_note_completion_date_title),
            initialValue = initialValue,
            onConfirm = { millis, selectedDate ->
                showDatePicker = false
                date = selectedDate

                onSetCompletionDate.invoke(millis)
            },
            onDecline = {
                showDatePicker = false
            }
        )
    }

    if (confirmCancelCompletionDialog.value) {
        AppAlertDialog(
            title = stringResource(resource = SharedR.strings.note_detail_cancel_completion_title),
            text = stringResource(resource = SharedR.strings.note_detail_cancel_completion_text),
            confirmTitle = stringResource(resource = SharedR.strings.confirm),
            cancelTitle = stringResource(resource = SharedR.strings.cancel),
            onDismiss = { confirmCancelCompletionDialog.value = false },
            onConfirmClick = {
                onCompleteClick?.invoke()
                confirmCancelCompletionDialog.value = false
            }
        )
    }
}


