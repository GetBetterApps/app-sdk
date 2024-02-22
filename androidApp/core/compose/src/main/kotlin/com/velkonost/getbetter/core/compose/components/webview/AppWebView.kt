package com.velkonost.getbetter.core.compose.components.webview

import android.annotation.SuppressLint
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import kotlinx.coroutines.launch

@SuppressLint("SetJavaScriptEnabled")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AppWebView(
    modifier: Modifier = Modifier,
    link: String?,
    sheetGesturesEnabled: Boolean = true,
    sheetState: ModalBottomSheetState,
) {

    val scope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetGesturesEnabled = sheetGesturesEnabled,
        sheetShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        sheetBackgroundColor = colorResource(resource = SharedR.colors.main_background),
        sheetContent = {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.9f)
            ) {
                AndroidView(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                    factory = { context ->
                        WebView(context).apply {
                            settings.javaScriptEnabled = true
                            settings.javaScriptCanOpenWindowsAutomatically = true
                            webViewClient = MyWebViewClient(
                                onBack = {
                                    scope.launch {
                                        sheetState.hide()
                                    }
                                }
                            )

                            settings.loadWithOverviewMode = true
                            settings.useWideViewPort = true

                            settings.builtInZoomControls = true
                            settings.setSupportZoom(true)
                            settings.domStorageEnabled = true
                            settings.loadsImagesAutomatically = true
                            settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                        }
                    },
                    update = { webView ->
                        link?.let { webView.loadUrl(it) }
                    }
                )
            }
        }
    ) {}
}

class MyWebViewClient(
    private val onBack: () -> Unit
) : WebViewClient() {

    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        return when {
            url?.contains("yookassaReturn") == true -> {
                onBack()
                true
            }

            else -> super.shouldOverrideUrlLoading(view, url)
        }
    }
}
