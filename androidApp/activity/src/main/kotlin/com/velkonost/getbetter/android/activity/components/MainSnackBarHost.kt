package com.velkonost.getbetter.android.activity.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.core.compose.extensions.borderBevel
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource


@Composable
internal fun MainSnackBarHost(snackBarHostState: SnackbarHostState) {
    SnackbarHost(
        hostState = snackBarHostState,
        snackbar = { snackbarData: SnackbarData ->
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .defaultMinSize(minHeight = 72.dp)
                    .borderBevel()
                    .padding(8.dp),
                shape = MaterialTheme.shapes.medium,
                color = colorResource(resource = SharedR.colors.button_gradient_start),
                tonalElevation = 12.dp,
                shadowElevation = 8.dp
            ) {
                Row(
                    modifier = Modifier.padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = snackbarData.visuals.message,
                        color = colorResource(resource = SharedR.colors.text_title),
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(16.dp, 12.dp, 8.dp, 12.dp)
                            .weight(1f)
                            .align(Alignment.CenterVertically)
                    )
                    snackbarData.visuals.actionLabel?.let { action ->
                        Text(
                            text = action.uppercase(),
                            color = MaterialTheme.colorScheme.background,
                            style = MaterialTheme.typography.labelLarge,
                            modifier = Modifier
                                .wrapContentWidth()
                                .wrapContentHeight()
                                .padding(16.dp)
                        )
                    }
                }
            }
        }
    )
}

@Composable
internal fun rememberSnackBarHostState() = remember { SnackbarHostState() }
