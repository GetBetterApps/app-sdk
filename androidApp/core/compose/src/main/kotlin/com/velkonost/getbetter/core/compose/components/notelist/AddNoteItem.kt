package com.velkonost.getbetter.core.compose.components.notelist

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.velkonost.getbetter.core.compose.components.ExpandableButtonItem
import com.velkonost.getbetter.core.compose.components.ExpandableButtonPanel
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun BoxScope.AddNoteItem(
    modifier: Modifier = Modifier,
    createGoalClick: () -> Unit,
    createNoteClick: () -> Unit
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
                title = stringResource(resource = SharedR.strings.create_goal_button),
                icon = painterResource(imageResource = SharedR.images.ic_goal)
            ) { createGoalClick.invoke() },

            ExpandableButtonItem(
                title = stringResource(resource = SharedR.strings.create_note_button),
                icon = painterResource(imageResource = SharedR.images.ic_note)
            ) { createNoteClick.invoke() }
        ),
    )
}