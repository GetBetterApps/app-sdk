package com.velkonost.getbetter.android.features.taskdetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.velkonost.getbetter.android.features.areadetail.AreaDetailScreen
import com.velkonost.getbetter.android.features.taskdetail.components.AbilityData
import com.velkonost.getbetter.android.features.taskdetail.components.TaskDetailHeader
import com.velkonost.getbetter.core.compose.components.Loader
import com.velkonost.getbetter.core.compose.components.details.AreaData
import com.velkonost.getbetter.shared.features.taskdetail.presentation.TaskDetailViewModel
import com.velkonost.getbetter.shared.features.taskdetail.presentation.contract.TaskDetailAction
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.stringResource
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TaskDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: TaskDetailViewModel
) {

    val state by viewModel.viewState.collectAsStateWithLifecycle()
    val scope = rememberCoroutineScope()

    val selectedAreaId = remember { mutableStateOf<Int?>(null) }
    val areaDetailSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )

    Box(modifier = modifier.fillMaxSize()) {
        if (state.isLoading || state.task == null) {
            Loader(modifier = Modifier.align(Alignment.Center))
        } else {
            LazyColumn(
                modifier = modifier
                    .padding(start = 20.dp, end = 20.dp)
                    .fillMaxSize(),
                contentPadding = PaddingValues(bottom = 220.dp)
            ) {
                item {
                    TaskDetailHeader(
                        isFavorite = state.task!!.isFavorite,
                        isFavoriteLoading = state.task!!.isFavoriteLoading,
                        onBackClick = {
                            viewModel.dispatch(TaskDetailAction.NavigateBack)
                        },
                        onFavoriteClick = {

                        }
                    )
                }

                item {
                    state.area?.let {
                        AreaData(area = it) {
                            scope.launch {
                                selectedAreaId.value = it.id
                                areaDetailSheetState.show()
                            }
                        }
                    }
                }

                item {
                    Text(
                        modifier = modifier.padding(top = 24.dp),
                        text = state.task!!.name,
                        style = MaterialTheme.typography.labelLarge.copy(fontSize = 18.sp),
                        color = colorResource(resource = SharedR.colors.text_light),
                    )
                }

                item {
                    Text(
                        modifier = modifier.padding(top = 16.dp),
                        text = stringResource(resource = SharedR.strings.task_what_to_do_title),
                        style = MaterialTheme.typography.labelMedium,
                        color = colorResource(resource = SharedR.colors.text_title)
                    )
                }

                item {
                    Text(
                        modifier = modifier.padding(top = 6.dp),
                        text = state.task!!.whatToDo,
                        style = MaterialTheme.typography.labelMedium,
                        color = colorResource(resource = SharedR.colors.text_primary)
                    )
                }

                item {
                    Text(
                        modifier = modifier.padding(top = 16.dp),
                        text = stringResource(resource = SharedR.strings.task_why_title),
                        style = MaterialTheme.typography.labelMedium,
                        color = colorResource(resource = SharedR.colors.text_title)
                    )
                }

                item {
                    Text(
                        modifier = modifier.padding(top = 6.dp),
                        text = state.task!!.why,
                        style = MaterialTheme.typography.labelMedium,
                        color = colorResource(resource = SharedR.colors.text_primary)
                    )
                }

                item {
                    Text(
                        modifier = modifier.padding(top = 16.dp),
                        text = stringResource(resource = SharedR.strings.task_abilities_title),
                        style = MaterialTheme.typography.labelMedium,
                        color = colorResource(resource = SharedR.colors.text_title)
                    )
                }

                items(state.task!!.abilities, key = { it.id!! }) { ability ->
                    AbilityData(
                        item = ability,
                        onClick = {

                        }
                    )
                }
            }
        }
    }

    AreaDetailScreen(
        modalSheetState = areaDetailSheetState,
        areaId = selectedAreaId.value,
        onAreaChanged = {
            viewModel.dispatch(TaskDetailAction.AreaChanged)
        }
    )

}