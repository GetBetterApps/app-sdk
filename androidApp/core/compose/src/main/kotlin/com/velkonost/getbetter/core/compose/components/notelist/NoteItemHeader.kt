package com.velkonost.getbetter.core.compose.components.notelist

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.core.compose.components.Loader
import com.velkonost.getbetter.shared.core.model.likes.LikeType
import com.velkonost.getbetter.shared.core.model.likes.LikesData
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.painterResource

@Composable
fun NoteItemHeader(
    modifier: Modifier = Modifier,
    areaName: String,
    taskName: String? = null,
    areaIcon: ImageResource,
    onLikeClick: () -> Unit,
    likesData: LikesData,
) {
    val interactionSource = remember { MutableInteractionSource() }

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = modifier.size(32.dp),
            painter = painterResource(imageResource = areaIcon),
            contentDescription = null
        )

        Column(
            modifier = modifier
                .padding(start = 6.dp)
                .fillMaxSize(0.9f)
        ) {
            Text(
                modifier = modifier.padding(bottom = 2.dp),
                text = areaName,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleMedium,
                color = colorResource(resource = SharedR.colors.text_primary)
            )

            AnimatedVisibility(visible = taskName != null) {
                Text(
                    modifier = modifier.padding(top = 2.dp),
                    text = taskName!!,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodySmall,
                    color = colorResource(resource = SharedR.colors.icon_inactive)
                )
            }

        }

        Spacer(modifier = modifier.weight(1f))

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
                    Loader(size = 32)
                }
            }
        }


    }
}