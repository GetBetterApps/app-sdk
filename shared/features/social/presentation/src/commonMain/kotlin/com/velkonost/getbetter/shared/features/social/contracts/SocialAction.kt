package com.velkonost.getbetter.shared.features.social.contracts

import com.velkonost.getbetter.shared.core.model.note.Note
import com.velkonost.getbetter.shared.core.vm.contracts.UIContract

sealed interface SocialAction : UIContract.Action {
    data object GeneralFeedLoadNextPage : SocialAction

    data object AreasFeedLoadNextPage : SocialAction

    data class NoteClick(val value: Note) : SocialAction

    data class NoteLikeClick(val value: Note) : SocialAction

    data object RefreshGeneralFeed : SocialAction

    data object RefreshAreasFeed : SocialAction

    data object HintClick : SocialAction
}