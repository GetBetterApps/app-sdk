package com.velkonost.getbetter.shared.features.taskdetail.presentation.contract

import com.velkonost.getbetter.shared.core.model.area.Area
import com.velkonost.getbetter.shared.core.model.task.TaskUI
import com.velkonost.getbetter.shared.core.vm.contracts.UIContract
import com.velkonost.getbetter.shared.features.createnote.presentation.contract.CreateNewNoteViewState

data class TaskDetailViewState(
    val isLoading: Boolean = false,
    val task: TaskUI? = null,
    val area: Area? = null,
    val createNewNoteViewState: CreateNewNoteViewState = CreateNewNoteViewState()
) : UIContract.State