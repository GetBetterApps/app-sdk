package com.velkonost.getbetter.android.features.notedetail.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.core.compose.components.HintButton
import com.velkonost.getbetter.core.compose.components.Loader
import com.velkonost.getbetter.shared.core.model.likes.LikeType
import com.velkonost.getbetter.shared.core.model.likes.LikesData
import com.velkonost.getbetter.shared.core.model.note.NoteType
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun NoteDetailHeader(
    modifier: Modifier = Modifier,
    noteType: NoteType,
    isNotePrivate: Boolean,
    likesData: LikesData,
    allowHide: Boolean,
    onBackClick: () -> Unit,
    onHideClick: () -> Unit,
    onLikeClick: () -> Unit,
    onHintClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    Row(
        modifier = modifier.padding(top = 40.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = modifier
                .size(42.dp)
                .background(
                    color = colorResource(resource = SharedR.colors.background_icon),
                    shape = MaterialTheme.shapes.small
                )
                .padding(4.dp)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) { onBackClick() },
            painter = painterResource(imageResource = SharedR.images.ic_arrow_back),
            contentDescription = null,
            colorFilter = ColorFilter.tint(
                color = colorResource(resource = SharedR.colors.icon_active)
            )
        )

        Text(
            modifier = modifier.padding(start = 12.dp),
            text = stringResource(
                resource =
                if (noteType == NoteType.Default) SharedR.strings.note_detail_title
                else SharedR.strings.goal_detail_title
            ),
            style = MaterialTheme.typography.headlineSmall,
            color = colorResource(resource = SharedR.colors.text_title)
        )

        HintButton(
            modifier = modifier.padding(start = 6.dp, top = 6.dp),
            onClick = onHintClick
        )

        Spacer(modifier.weight(1f))

        if (isNotePrivate) {
            Image(
                modifier = modifier
                    .size(42.dp)
                    .background(
                        color = colorResource(resource = SharedR.colors.background_icon),
                        shape = MaterialTheme.shapes.small
                    )
                    .padding(4.dp)
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null
                    ) { onBackClick() },
                painter = painterResource(imageResource = SharedR.images.ic_lock),
                contentDescription = null,
                colorFilter = ColorFilter.tint(
                    color = colorResource(resource = SharedR.colors.icon_inactive)
                )
            )
        } else {
            if (allowHide) {
                Image(
                    modifier = modifier
                        .padding(end = 12.dp)
                        .size(24.dp)
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null,
                            onClick = onHideClick
                        ),
                    painter = painterResource(imageResource = SharedR.images.ic_hide),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(color = colorResource(resource = SharedR.colors.icon_inactive))
                )
            }

            Box {
                AnimatedContent(targetState = likesData.isLikesLoading, label = "") {
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
                                    imageResource = if (likesData.userLike == LikeType.Positive) SharedR.images.ic_heart
                                    else SharedR.images.ic_heart_empty
                                ),
                                contentDescription = null,
                                colorFilter = ColorFilter.tint(color = colorResource(resource = SharedR.colors.button_gradient_start))
                            )
                            Text(
                                text = likesData.totalLikes.toString(),
                                color = colorResource(resource = SharedR.colors.text_primary),
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    } else {
                        Loader(size = 32.dp)
                    }
                }
            }
        }
    }
}