package com.velkonost.getbetter.shared.features.calendars.presentation.model

import com.velkonost.getbetter.shared.core.model.EntityType
import com.velkonost.getbetter.shared.core.model.user.ActionType
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

    data object UserCompletedTask : UserActionType
}

val UserAction.type: UserActionType?
    get() = when {
        entityType == EntityType.User && actionType == ActionType.Add -> UserActionType.UserRegistered

        entityType == EntityType.Area && actionType == ActionType.Add -> UserActionType.UserCreatedArea
        entityType == EntityType.Area && actionType == ActionType.Join -> UserActionType.UserJoinedArea
        entityType == EntityType.Area && actionType == ActionType.Leave -> UserActionType.UserLeavedArea

        entityType == EntityType.Note && actionType == ActionType.Add -> UserActionType.UserCreatedNote
        entityType == EntityType.Note && actionType == ActionType.Complete -> UserActionType.UserCompletedGoal
        entityType == EntityType.SubGoal && actionType == ActionType.Complete -> UserActionType.UserCompletedSubGoal

        entityType == EntityType.Comment && actionType == ActionType.Add -> UserActionType.UserCreatedComment
        entityType == EntityType.Comment && actionType == ActionType.AddInbox -> UserActionType.UserGotComment

        entityType == EntityType.Like && actionType == ActionType.Add -> UserActionType.UserCreatedLike
        entityType == EntityType.Like && actionType == ActionType.AddInbox -> UserActionType.UserGotLike

        entityType == EntityType.Follow && actionType == ActionType.Add -> UserActionType.UserFollowed
        entityType == EntityType.Follower && actionType == ActionType.Add -> UserActionType.UserGotFollower

        entityType == EntityType.Task && actionType == ActionType.Complete -> UserActionType.UserCompletedTask

        else -> null
    }
