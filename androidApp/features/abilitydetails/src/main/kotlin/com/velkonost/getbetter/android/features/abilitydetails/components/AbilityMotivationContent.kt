package com.velkonost.getbetter.android.features.abilitydetails.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.velkonost.getbetter.shared.core.model.task.Affirmation

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AbilityMotivationContent(
    modifier: Modifier = Modifier,
    items: List<Affirmation>
) {

    val listState = rememberLazyListState()
    val context = LocalContext.current

    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn(
            state = listState,
            flingBehavior = rememberSnapFlingBehavior(listState),
        ) {
            items(items, key = { it.id }) { item ->
                SubcomposeAsyncImage(
                    modifier = modifier
                        .fillMaxSize()
                        .align(Alignment.Center),
                    model = ImageRequest
                        .Builder(context)
                        .data(item.imageUrl)
                        .crossfade(true)
                        .build(),
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
//            loading = {
//                Box {
//                    CircularProgressIndicator(
//                        modifier = modifier
//                            .size(64.dp)
//                            .align(Alignment.Center),
//                        color = colorResource(resource = SharedR.colors.text_light).copy(alpha = 0.5f)
//                    )
//                }
//            },
//            error = {
//                Image(
//                    modifier = modifier
//                        .size(64.dp)
//                        .clip(MaterialTheme.shapes.small),
//                    painter = painterResource(imageResource = SharedR.images.logo),
//                    contentDescription = null
//                )
//            }
                )
            }
        }
    }

}