package com.velkonost.getbetter.core.compose.components

import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource

@Composable
fun VersionName(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val manager: PackageManager = context.packageManager
    val info: PackageInfo = manager.getPackageInfo(context.packageName, 0)
    val version = info.versionName

    Text(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 40.dp),
        text = version,
        color = colorResource(resource = SharedR.colors.text_unimportant_color),
        style = MaterialTheme.typography.labelMedium,
        textAlign = TextAlign.Center
    )
}