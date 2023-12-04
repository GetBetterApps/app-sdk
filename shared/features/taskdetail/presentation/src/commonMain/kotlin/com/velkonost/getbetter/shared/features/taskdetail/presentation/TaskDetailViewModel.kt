package com.velkonost.getbetter.shared.features.taskdetail.presentation

import AreasRepository
import com.velkonost.getbetter.shared.core.util.isLoading
import com.velkonost.getbetter.shared.core.util.onSuccess
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
    private val areasRepository: AreasRepository
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
                        task = task,
                        area = task?.area
                    )
                )
            }
        }
    }

    override fun dispatch(action: TaskDetailAction) = when (action) {
        is TaskDetailAction.NavigateBack -> emit(action)
        is TaskDetailAction.AreaChanged -> obtainAreaChanged()
        is TaskDetailAction.FavoriteClick -> obtainFavoriteClick()
    }

    private fun obtainAreaChanged() {
        launchJob {
            viewState.value.area?.let { area ->
                areasRepository.fetchAreaDetails(area.id) collectAndProcess {
                    onSuccess {
                        emit(viewState.value.copy(area = it))
                    }
                }
            }
        }
    }

    private fun obtainFavoriteClick() {
        launchJob {
            val taskId = viewState.value.task!!.id!!
            val request =
                if (viewState.value.task!!.isFavorite) tasksRepository.removeFromFavorite(taskId)
                else tasksRepository.addToFavorite(taskId)

            request collectAndProcess {
                isLoading {
                    val task = viewState.value.task?.copy(isFavoriteLoading = it)
                    emit(viewState.value.copy(task = task))
                }
                onSuccess {
                    val updatedFavorite = !viewState.value.task!!.isFavorite
                    val task = viewState.value.task?.copy(isFavorite = updatedFavorite)
                    emit(viewState.value.copy(task = task))
                }
            }
        }
    }

}