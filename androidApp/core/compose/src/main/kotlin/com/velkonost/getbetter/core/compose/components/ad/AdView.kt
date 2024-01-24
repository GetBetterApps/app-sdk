package com.velkonost.getbetter.core.compose.components.ad

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.velkonost.getbetter.core.compose.components.Loader
import com.velkonost.getbetter.core.compose.components.PrimaryBox
import com.velkonost.getbetter.shared.resources.SharedR
import com.yandex.mobile.ads.banner.BannerAdEventListener
import com.yandex.mobile.ads.banner.BannerAdSize
import com.yandex.mobile.ads.banner.BannerAdView
import com.yandex.mobile.ads.common.AdRequest
import com.yandex.mobile.ads.common.AdRequestError
import com.yandex.mobile.ads.common.ImpressionData
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.stringResource
import java.util.Locale

@Composable
fun AdView(
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current
    val configuration = LocalConfiguration.current

    val adSize: BannerAdSize = remember {
        val adWidth = configuration.screenWidthDp - 40
        val maxAdHeight = 300

        BannerAdSize.inlineSize(context, adWidth, maxAdHeight)
    }

    PrimaryBox(
        modifier = modifier
            .padding(PaddingValues(horizontal = 20.dp))
            .height(300.dp),
        padding = 0
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(bottom = 30.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier.weight(1f))
            Loader()
            Text(
                text = stringResource(resource = SharedR.strings.ad_title).replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.ROOT
                    ) else it.toString()
                },
                color = colorResource(resource = SharedR.colors.onboarding_background_gradient_start),
                style = MaterialTheme.typography.headlineSmall,
                fontStyle = FontStyle.Italic
            )
            Spacer(modifier.weight(1f))
        }

        Column {
            Spacer(modifier.weight(1f))
            Row {
                Spacer(modifier.weight(1f))
                AndroidView(
                    modifier = modifier
                        .clip(MaterialTheme.shapes.medium),
                    factory = {
                        BannerAdView(it)
                    },
                    update = {
                        it.apply {
                            setAdSize(adSize)
                            setAdUnitId("R-M-5517748-1")
                            setBannerAdEventListener(object : BannerAdEventListener {
                                override fun onAdLoaded() {
                                }

                                override fun onAdFailedToLoad(adRequestError: AdRequestError) {
                                }

                                override fun onAdClicked() {
                                    // Called when a click is recorded for an ad.
                                }

                                override fun onLeftApplication() {
                                    // Called when user is about to leave application (e.g., to go to the browser), as a result of clicking on the ad.
                                }

                                override fun onReturnedToApplication() {
                                    // Called when user returned to application after click.
                                }

                                override fun onImpression(impressionData: ImpressionData?) {
                                    // Called when an impression is recorded for an ad.
                                }
                            })
                            loadAd(
                                AdRequest.Builder()
                                    // Methods in the AdRequest.Builder class can be used here to specify individual options settings.
                                    .build()
                            )
                        }

                    }
                )
                Spacer(modifier.weight(1f))
            }
            Spacer(modifier.weight(1f))
        }
    }
}