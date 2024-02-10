package com.velkonost.getbetter.android.features.subscription.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.core.compose.components.AppButton
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun OffersSheet(
    modifier: Modifier = Modifier,
    modalSheetState: ModalBottomSheetState,
) {

    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        sheetBackgroundColor = colorResource(resource = SharedR.colors.background_item),
        sheetContent = {
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 48.dp, top = 16.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {

                    Row {
                        Spacer(modifier.weight(1f))
                        Image(
                            painter = painterResource(imageResource = SharedR.images.ic_grabber),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(
                                color = colorResource(resource = SharedR.colors.button_gradient_start)
                            )
                        )
                        Spacer(modifier.weight(1f))
                    }

                    OfferView(
                        title = stringResource(resource = SharedR.strings.offer_month_title),
                        price = stringResource(resource = SharedR.strings.offer_month_price),
                        text = stringResource(resource = SharedR.strings.offer_month_text),
                        selected = false
                    )

                    OfferView(
                        title = stringResource(resource = SharedR.strings.offer_year_title),
                        price = stringResource(resource = SharedR.strings.offer_year_price),
                        text = stringResource(resource = SharedR.strings.offer_year_text),
                        selected = false
                    )

                    OfferView(
                        title = stringResource(resource = SharedR.strings.offer_unlimited_title),
                        price = stringResource(resource = SharedR.strings.offer_unlimited_price),
                        text = stringResource(resource = SharedR.strings.offer_unlimited_text),
                        selected = false
                    )

                    AppButton(
                        modifier = modifier.padding(top = 32.dp),
                        labelText = stringResource(resource = SharedR.strings.confirm),
                        isLoading = false,
                        onClick = {

                        }
                    )
                }
            }
        }
    ) {

    }
}

@Composable
fun OfferView(
    modifier: Modifier = Modifier,
    title: String,
    price: String,
    text: String,
    selected: Boolean
) {

    Row(
        modifier = modifier
            .padding(top = 16.dp)
            .border(
                width = if (selected) 1.dp else 0.dp,
                color = colorResource(resource = SharedR.colors.onboarding_background_gradient_start)
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = modifier.size(32.dp),
            painter = painterResource(imageResource = SharedR.images.emoji_1),
            contentDescription = null
        )

        Column {
            Text(
                text = title,
                color = colorResource(resource = SharedR.colors.text_primary),
                style = MaterialTheme.typography.titleSmall
            )
            Text(
                text = text,
                color = colorResource(resource = SharedR.colors.text_secondary),
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(modifier.weight(1f))

        Text(
            text = price,
            color = colorResource(resource = SharedR.colors.text_primary),
            style = MaterialTheme.typography.bodyMedium
        )
    }

}