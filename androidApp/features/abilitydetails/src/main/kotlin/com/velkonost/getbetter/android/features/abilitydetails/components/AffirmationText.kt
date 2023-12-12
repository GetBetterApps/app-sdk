package com.velkonost.getbetter.android.features.abilitydetails.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource

@Composable
fun BoxScope.AffirmationText(
    modifier: Modifier = Modifier,
    text: String
) {
    Column(
        modifier = modifier
            .align(Alignment.Center)
            .padding(16.dp)
            .background(
                color = colorResource(
                    resource = SharedR.colors.background_item
                ).copy(alpha = 0.4f),
                shape = MaterialTheme.shapes.medium
            )
            .padding(16.dp)
    ) {
        Text(
            text = text,
            color = colorResource(resource = SharedR.colors.text_title),
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center
        )
    }

}