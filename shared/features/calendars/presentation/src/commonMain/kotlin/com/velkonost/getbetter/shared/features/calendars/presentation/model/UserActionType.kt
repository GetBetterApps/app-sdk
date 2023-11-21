package com.velkonost.getbetter.shared.features.calendars.presentation.model

import com.velkonost.getbetter.shared.core.model.user.UserAction

sealed interface UserActionType {
    data object UserRegistered : UserActionType

    data object UserCreatedNote : UserActionType

    data object UserCreatedArea : UserActionType

    data object UserJoinedArea : UserActionType

    data object UserLeavedArea : UserActionType

    data object UserCompletedGoal : UserActionType

    data object UserCompletedSubGoal : UserActionType

    data object UserCreatedComment : UserActionType

    data object UserGotComment : UserActionType

    data object UserCreatedLike : UserActionType

    data object UserGotLike : UserActionType

    data object UserFollowed : UserActionType

    data object UserGotFollower : UserActionType
}

val UserAction.type: UserActionType
    get() {

    }