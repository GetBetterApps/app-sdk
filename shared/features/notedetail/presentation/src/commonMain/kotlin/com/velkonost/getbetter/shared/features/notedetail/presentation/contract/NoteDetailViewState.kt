package com.velkonost.getbetter.shared.features.notedetail.presentation.contract

import com.velkonost.getbetter.shared.core.model.area.Area
import com.velkonost.getbetter.shared.core.model.comments.Comment
import com.velkonost.getbetter.shared.core.model.likes.LikeType
import com.velkonost.getbetter.shared.core.model.likes.LikesData
import com.velkonost.getbetter.shared.core.model.note.Note
import com.velkonost.getbetter.shared.core.model.note.NoteType
import com.velkonost.getbetter.shared.core.model.task.TaskUI
import com.velkonost.getbetter.shared.core.model.ui.SubNoteUI
import com.velkonost.getbetter.shared.core.model.ui.TagUI
import com.velkonost.getbetter.shared.core.model.user.UserInfoShort
import com.velkonost.getbetter.shared.core.util.DatetimeFormatter.convertToLocalDatetime
import com.velkonost.getbetter.shared.core.vm.contracts.UIContract
import dev.icerock.moko.resources.desc.StringDesc

data class NoteDetailViewState(
    val isLoading: Boolean = false,
    val isCompleteGoalLoading: Boolean = false,

    val initialItem: Note? = null,

    val noteState: NoteState = NoteState.View,

    val noteType: NoteType = NoteType.Default,
    val isNotePrivate: Boolean = true,
    val noteText: String = "",

    val tags: List<TagUI> = emptyList(),
    val newTag: TagUI = TagUI(),

    val subNotes: List<SubNoteUI> = emptyList(),
    val newSubNote: SubNoteUI = SubNoteUI(),

    val expectedCompletionDate: Long? = null,
    val completionDate: Long? = null,

    val expectedCompletionDateStr: StringDesc? = expectedCompletionDate?.convertToLocalDatetime(),
    val completionDateStr: StringDesc? = completionDate?.convertToLocalDatetime(),

    val area: Area? = null,
    val task: TaskUI? = null,
    val authorLoading: Boolean = true,
    val author: UserInfoShort? = null,

    val allowEdit: Boolean = false,
    val allowHide: Boolean = false,

    val likesData: LikesData = LikesData(totalLikes = 0, userLike = LikeType.None),
    val commentsData: CommentsData = CommentsData()
) : UIContract.State

data class CommentsData(
    val isLoading: Boolean = true,
    val commentText: String = "",
    val comments: List<Comment> = emptyList()
)

enum class NoteState {
    View, Editing
}