package com.velkonost.getbetter.android.features.abilitydetails.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest

@Composable
fun AbilityMotivationContent(
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current
    var imageRequest = ImageRequest.Builder(context)
        .data("http://62.113.117.236/affirmations/image")
        .crossfade(true)
        .build()


    Box(modifier = modifier.fillMaxSize()) {
        SubcomposeAsyncImage(
            modifier = modifier
                .fillMaxSize()
                .align(Alignment.Center)
                .clickable {
                    imageRequest = ImageRequest
                        .Builder(context)
                        .data("http://62.113.117.236/affirmations/image")
                        .crossfade(true)
                        .build()
                },
            model = imageRequest,
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