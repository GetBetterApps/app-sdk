package com.velkonost.getbetter.shared.features.notes.data.remote

object Route {
    private const val PREFIX = "notes"

    const val CREATE_NEW_NOTE = "$PREFIX/createNewNote"
    const val EDIT_NOTE = "$PREFIX/editNote"
    const val EDIT_SUB_NOTE = "$PREFIX/editSubNote"
    const val GET_USER_NOTES = "$PREFIX/getUserNotes"
    const val COMPLETE_GOAL = "$PREFIX/completeGoal"
    const val UNCOMPLETE_GOAL = "$PREFIX/uncompleteGoal"
    const val COMPLETE_SUB_GOAL = "$PREFIX/completeSubGoal"
    const val UNCOMPLETE_SUB_GOAL = "$PREFIX/uncompleteSubGoal"
    const val DELETE_NOTE = "$PREFIX/deleteNote"
    const val DELETE_SUB_NOTE = "$PREFIX/deleteSubNote"
    const val GET_NOTE_DETAILS = "$PREFIX/getNoteDetails"
}