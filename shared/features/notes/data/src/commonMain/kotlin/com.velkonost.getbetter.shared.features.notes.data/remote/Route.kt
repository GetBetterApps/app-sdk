package com.velkonost.getbetter.shared.features.notes.data.remote

object Route {
    private const val PREFIX = "notes"

    const val CREATE_NEW_NOTE = "$PREFIX/new"
    const val EDIT_NOTE = "$PREFIX/edit"
    const val EDIT_SUB_NOTE = "$PREFIX/editSubNote"
    const val GET_USER_NOTES = "$PREFIX/userNotes"

    const val COMPLETE_GOAL = "$PREFIX/complete"
    const val UNCOMPLETE_GOAL = "$PREFIX/uncomplete"
    const val COMPLETE_SUB_GOAL = "$PREFIX/completeSubGoal"
    const val UNCOMPLETE_SUB_GOAL = "$PREFIX/uncompleteSubGoal"

    const val DELETE_NOTE = "$PREFIX/delete"
    const val DELETE_SUB_NOTE = "$PREFIX/deleteSubNote"

    const val GET_NOTE_DETAILS = "$PREFIX/details"

    const val GET_OTHER_USER_NOTES = "$PREFIX/otherUserNotes"
    const val GET_NOTES_BY_TASK = "$PREFIX/byTask"
    const val GET_NOTES_BY_ABILITY = "$PREFIX/byAbility"
}