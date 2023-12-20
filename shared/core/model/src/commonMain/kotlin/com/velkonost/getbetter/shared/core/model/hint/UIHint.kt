package com.velkonost.getbetter.shared.core.model.hint

import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.desc.Resource
import dev.icerock.moko.resources.desc.StringDesc

enum class UIHint(
    val title: StringDesc? = null,
    val text: StringDesc
) {

    SocialAll(
        title = StringDesc.Resource(SharedR.strings.hint_social_all_title),
        text = StringDesc.Resource(SharedR.strings.hint_social_all_text)
    ),

    NoteComments(
        text = StringDesc.Resource(SharedR.strings.hint_note_comments_text)
    ),

    DiaryNotes(
        title = StringDesc.Resource(SharedR.strings.hint_diary_notes_title),
        text = StringDesc.Resource(SharedR.strings.hint_diary_notes_text)
    ),

    DiaryCreateNote(
        title = StringDesc.Resource(SharedR.strings.hint_diary_create_note_title),
        text = StringDesc.Resource(SharedR.strings.hint_diary_create_note_text)
    ),

    DiaryCreateGoal(
        title = StringDesc.Resource(SharedR.strings.hint_diary_create_goal_title),
        text = StringDesc.Resource(SharedR.strings.hint_diary_create_goal_text)
    ),

    DiaryNoteDetail(
        text = StringDesc.Resource(SharedR.strings.hint_diary_note_detail_text)
    ),

    DiaryGoalDetail(
        text = StringDesc.Resource(SharedR.strings.hint_diary_goal_detail_text)
    ),

    DiaryAreas(
        title = StringDesc.Resource(SharedR.strings.hint_diary_areas_title),
        text = StringDesc.Resource(SharedR.strings.hint_diary_areas_text)
    ),

    DiaryCreateArea(
        title = StringDesc.Resource(SharedR.strings.hint_diary_create_area_title),
        text = StringDesc.Resource(SharedR.strings.hint_diary_create_area_text),
    ),

    DiaryAddArea(
        title = StringDesc.Resource(SharedR.strings.hint_diary_add_area_title),
        text = StringDesc.Resource(SharedR.strings.hint_diary_add_area_text)
    ),

    DiaryAreaDetail(
        text = StringDesc.Resource(SharedR.strings.hint_diary_area_detail_text)
    ),

    DiaryTasks(
        title = StringDesc.Resource(SharedR.strings.hint_diary_tasks_title),
        text = StringDesc.Resource(SharedR.strings.hint_diary_tasks_text)
    ),

    DiaryTaskDetail(
        title = StringDesc.Resource(SharedR.strings.hint_diary_task_detail_title),
        text = StringDesc.Resource(SharedR.strings.hint_diary_task_detail_text)
    ),

    Calendars(
        title = StringDesc.Resource(SharedR.strings.hint_calendars_title),
        text = StringDesc.Resource(SharedR.strings.hint_calendars_text)
    ),

    Abilities(
        title = StringDesc.Resource(SharedR.strings.hint_abilities_title),
        text = StringDesc.Resource(SharedR.strings.hint_abilities_text)
    )

}