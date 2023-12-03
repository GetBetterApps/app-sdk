package com.velkonost.getbetter.shared.features.taskdetail.presentation

import com.velkonost.getbetter.shared.core.vm.BaseViewModel
import com.velkonost.getbetter.shared.core.vm.SavedStateHandle
import com.velkonost.getbetter.shared.features.notes.api.NotesRepository
import com.velkonost.getbetter.shared.features.taskdetail.presentation.contract.TaskDetailAction
import com.velkonost.getbetter.shared.features.taskdetail.presentation.contract.TaskDetailEvent
import com.velkonost.getbetter.shared.features.taskdetail.presentation.contract.TaskDetailNavigation
import com.velkonost.getbetter.shared.features.taskdetail.presentation.contract.TaskDetailViewState
import com.velkonost.getbetter.shared.features.tasks.api.TasksRepository
import kotlinx.coroutines.flow.collectLatest

class TaskDetailViewModel
internal constructor(
    savedStateHandle: SavedStateHandle,
    private val notesRepository: NotesRepository,
    private val tasksRepository: TasksRepository,
) : BaseViewModel<TaskDetailViewState, TaskDetailAction, TaskDetailNavigation, TaskDetailEvent>(
    initialState = TaskDetailViewState(),
    savedStateHandle = savedStateHandle
) {

    private val task = savedStateHandle.task.stateInWhileSubscribed(initialValue = null)

    init {
        launchJob {
            task.collectLatest { task ->
                emit(
                    viewState.value.copy(
                        isLoading = false,
                        task = task
                    )
                )
            }
        }
    }

    override fun dispatch(action: TaskDetailAction) = when (action) {
        is TaskDetailAction.NavigateBack -> emit(action)
    }


}