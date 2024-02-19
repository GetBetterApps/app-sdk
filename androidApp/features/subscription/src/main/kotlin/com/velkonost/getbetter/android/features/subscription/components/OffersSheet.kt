package com.velkonost.getbetter.android.features.subscription.components

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.core.compose.components.AppButton
import com.velkonost.getbetter.shared.features.subscription.presentation.model.SubscriptionType
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource
import ru.yoomoney.sdk.kassa.payments.Checkout
import ru.yoomoney.sdk.kassa.payments.checkoutParameters.Amount
import ru.yoomoney.sdk.kassa.payments.checkoutParameters.PaymentMethodType
import ru.yoomoney.sdk.kassa.payments.checkoutParameters.PaymentParameters
import ru.yoomoney.sdk.kassa.payments.checkoutParameters.SavePaymentMethod
import java.math.BigDecimal
import java.util.Currency

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun OffersSheet(
    modifier: Modifier = Modifier,
    modalSheetState: ModalBottomSheetState,
    items: List<SubscriptionType>,
    selectedItem: SubscriptionType,
    itemClick: (SubscriptionType) -> Unit
) {

    val paymentLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
    }

    val context = LocalContext.current

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
                Column(verticalArrangement = Arrangement.SpaceEvenly) {

                    Row {
                        Spacer(modifier.weight(1f))
                        Image(
                            painter = painterResource(imageResource = SharedR.images.ic_grabber),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(
                                color = colorResource(resource = SharedR.colors.onboarding_background_gradient_start)
                            )
                        )
                        Spacer(modifier.weight(1f))
                    }

                    Spacer(modifier.height(16.dp))

                    items.forEach {
                        OfferView(
                            title = it.title.toString(context),
                            price = it.price.toString(context),
                            text = it.text.toString(context),
                            selected = it == selectedItem,
                            onClick = {
                                itemClick(it)
                            }
                        )
                    }

                    Row {
                        Spacer(modifier.weight(1f))
                        AppButton(
                            modifier = modifier.padding(top = 32.dp),
                            labelText = stringResource(resource = SharedR.strings.subscription_confirm),
                            isLoading = false,
                            onClick = {
                                val paymentParameters = PaymentParameters(
                                    amount = Amount(BigDecimal.TEN, Currency.getInstance("RUB")),
                                    title = "Название товара",
                                    subtitle = "Описание товара",
                                    clientApplicationKey = "live_MzMxMjA5lf8FLM_nMtwUESA8dc8wrvO3X4rtmPgTdng", // ключ для клиентских приложений из личного кабинета ЮKassa (https://yookassa.ru/my/api-keys-settings)
                                    shopId = "331209", // идентификатор магазина ЮKassa
                                    paymentMethodTypes = setOf(
                                        PaymentMethodType.BANK_CARD,
                                        PaymentMethodType.SBERBANK,
                                        PaymentMethodType.SBP,
                                        PaymentMethodType.YOO_MONEY
                                    ),
                                    savePaymentMethod = SavePaymentMethod.OFF, // флаг выключенного сохранения платежного метода,
                                    authCenterClientId = "ej2j4fcjn0p7va4nqtj28viqn9gn85mb", // идентификатор, полученный при регистрации приложения на сайте https://yookassa.ru
//                                    userPhoneNumber = "+79041234567", // номер телефона пользователя. Используется для автозаполнения поля при оплате через SberPay. Поддерживаемый формат данных: "+7XXXXXXXXXX".
                                )
                                val intent =
                                    Checkout.createTokenizeIntent(context, paymentParameters)
                                paymentLauncher.launch(intent)
                            }
                        )
                        Spacer(modifier.weight(1f))
                    }
                    Spacer(modifier.height(32.dp))
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
    selected: Boolean,
    onClick: () -> Unit
) {

    Row(
        modifier = modifier
            .padding(top = 16.dp)
            .shadow(
                elevation = if (selected) 8.dp else 0.dp,
                shape = MaterialTheme.shapes.medium,
            )
            .background(
                color = colorResource(resource = SharedR.colors.background_item),
                shape = MaterialTheme.shapes.medium
            )
            .border(
                width = 1.dp,
                color = colorResource(
                    resource = if (!selected) SharedR.colors.text_primary
                    else SharedR.colors.onboarding_background_gradient_start
                ),
                shape = MaterialTheme.shapes.medium
            )
            .padding(16.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {

        AnimatedContent(targetState = selected, label = "") {
            Image(
                modifier = modifier.size(32.dp),
                painter = painterResource(
                    imageResource = if (it) SharedR.images.emoji_59
                    else SharedR.images.emoji_11
                ),
                contentDescription = null
            )
        }

        Column(modifier.padding(start = 12.dp)) {
            Text(
                text = title,
                color = colorResource(resource = SharedR.colors.text_title),
                style = MaterialTheme.typography.titleSmall
            )
            Text(
                text = text,
                color = colorResource(resource = SharedR.colors.text_primary),
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(modifier.weight(1f))

        Text(
            text = price,
            color = colorResource(resource = SharedR.colors.text_title),
            style = MaterialTheme.typography.titleMedium
        )
    }

}