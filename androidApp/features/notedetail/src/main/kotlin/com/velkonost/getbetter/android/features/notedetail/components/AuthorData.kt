package com.velkonost.getbetter.android.features.notedetail.components

import android.graphics.BitmapFactory
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.velkonost.getbetter.core.compose.components.Loader
import com.velkonost.getbetter.core.compose.components.PrimaryBox
import com.velkonost.getbetter.shared.core.model.user.UserInfoShort
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.painterResource

@Composable
fun AuthorData(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    author: UserInfoShort?,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    PrimaryBox(padding = 0) {
        AnimatedContent(targetState = isLoading, label = "") {
            Row(
                modifier = modifier
                    .padding(16.dp)
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null,
                        onClick = onClick
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                if (it) {
                    Spacer(modifier.weight(1f))
                    Loader(size = 32)
                    Spacer(modifier.weight(1f))
                } else {

                    if (author?.avatar != null) {
                        author.avatar?.let { avatarBytes ->
                            SubcomposeAsyncImage(
                                modifier = modifier
                                    .size(32.dp)
                                    .clip(MaterialTheme.shapes.small),
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(
                                        BitmapFactory.decodeByteArray(
                                            avatarBytes, 0, avatarBytes.size
                                        )
                                    )
                                    .build(),
                                contentScale = ContentScale.Crop,
                                contentDescription = null,
                            )
                        }
                    } else {
                        Image(
                            modifier = modifier
                                .size(32.dp)
                                .clip(MaterialTheme.shapes.small),
                            painter = painterResource(imageResource = SharedR.images.logo),
                            contentDescription = null
                        )
                    }

                    Text(
                        modifier = modifier
                            .padding(start = 12.dp)
                            .fillMaxWidth(0.8f),
                        text = author?.displayName ?: "",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = colorResource(resource = SharedR.colors.text_primary),
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    }

}