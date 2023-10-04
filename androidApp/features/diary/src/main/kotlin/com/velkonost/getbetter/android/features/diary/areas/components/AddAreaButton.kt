package com.velkonost.getbetter.android.features.diary.areas.components

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.velkonost.getbetter.core.compose.components.ExpandableButtonItem
import com.velkonost.getbetter.core.compose.components.ExpandableButtonPanel
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun BoxScope.AddAreaButton(
    modifier: Modifier = Modifier,
    addExistingClick: () -> Unit,
    createNewClick: () -> Unit
) {
    ExpandableButtonPanel(
        modifier = modifier,
        primaryItem = ExpandableButtonItem(
            title = stringResource(resource = SharedR.strings.cancel),
            icon = painterResource(imageResource = SharedR.images.ic_plus),
            iconExpanded = painterResource(imageResource = SharedR.images.ic_arrow_back)
        ),
        items = listOf(
            ExpandableButtonItem(
                title = stringResource(resource = SharedR.strings.diary_areas_add_existing_title),
                icon = painterResource(imageResource = SharedR.images.ic_grid)
            ) { addExistingClick.invoke() },

            ExpandableButtonItem(
                title = stringResource(resource = SharedR.strings.diary_areas_create_new_title),
                icon = painterResource(imageResource = SharedR.images.ic_edit)
            ) { createNewClick.invoke() }
        ),
    )
}

