package com.velkonost.getbetter.core.compose.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ColumnScope.WeightedSpacer() {
    Spacer(modifier = Modifier.weight(1f))
}

@Composable
fun RowScope.WeightedSpacer() {
    Spacer(modifier = Modifier.weight(1f))
}