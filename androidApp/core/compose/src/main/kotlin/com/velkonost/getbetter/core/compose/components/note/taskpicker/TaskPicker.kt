package com.velkonost.getbetter.core.compose.components.note.taskpicker

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.velkonost.getbetter.core.compose.components.PrimaryBox
import com.velkonost.getbetter.core.compose.extensions.horizontalFadingEdge
import com.velkonost.getbetter.shared.core.model.task.TaskUI
import com.velkonost.getbetter.shared.core.util.randomUUID
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun TaskPicker(
    modifier: Modifier = Modifier,
    forceSelectedTask: TaskUI?,
    tasks: List<TaskUI?>,
    tasksPagerState: PagerState = rememberPagerState(initialPage = 0, pageCount = { tasks.size }),
    selectedTask: TaskUI?,
    isTaskPickerVisible: MutableState<Boolean>,
    modalSheetState: ModalBottomSheetState,
    onTaskSelect: (TaskUI?) -> Unit
) {

    val scope = rememberCoroutineScope()

    PrimaryBox(padding = 0) {
        Column {
            TaskPickerHeader(
                selectedTask = selectedTask,
                availableTasksAmount = tasks.size,
                isTaskPickerVisible = isTaskPickerVisible,
            )

            AnimatedVisibility(visible = isTaskPickerVisible.value) {
                HorizontalPager(
                    modifier = modifier
                        .height(150.dp)
                        .fillMaxWidth()
                        .horizontalFadingEdge()
                        .padding(bottom = 16.dp),
                    state = tasksPagerState,
                    contentPadding = PaddingValues(start = 16.dp, end = 16.dp),
                    key = {
                        if (tasks.isNotEmpty()) {
                            if (it == 0) {
                                "empty"
                            } else if (tasks.lastIndex >= it) tasks[it]!!.id.toString()
                            else randomUUID()
                        } else randomUUID()
                    }
                ) {
                    if (tasks.size <= 1) {
                        return@HorizontalPager
                    }

                    if (it == 0) {
                        TaskPickerEmptyItem()
                    } else {
                        TaskPickerItem(task = tasks[it]!!)
                    }
                }
            }
        }
    }

    LaunchedEffect(modalSheetState.currentValue) {
        scope.launch {
            tasksPagerState.animateScrollToPage(0)
        }
    }

    LaunchedEffect(tasks) {
        snapshotFlow { tasksPagerState.currentPage }
            .collect { page ->
                if (isTaskPickerVisible.value) {
                    if (tasks.size > 1) {
                        onTaskSelect.invoke(
                            if (page == 0) {
                                null
                            } else tasks[page]
                        )
                    } else {
                        isTaskPickerVisible.value = false
                    }
                }
            }
    }

}