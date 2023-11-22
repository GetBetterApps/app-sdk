package com.velkonost.getbetter.shared.features.profiledetail.presentation

import com.velkonost.getbetter.shared.core.model.EntityType
import com.velkonost.getbetter.shared.core.model.likes.LikeType
import com.velkonost.getbetter.shared.core.model.note.Note
import com.velkonost.getbetter.shared.core.util.PagingConfig
import com.velkonost.getbetter.shared.core.util.isLoading
import com.velkonost.getbetter.shared.core.util.onSuccess
import com.velkonost.getbetter.shared.core.util.reset
import com.velkonost.getbetter.shared.core.vm.BaseViewModel
import com.velkonost.getbetter.shared.features.follows.api.FollowsRepository
import com.velkonost.getbetter.shared.features.likes.api.LikesRepository
import com.velkonost.getbetter.shared.features.notes.api.NotesRepository
import com.velkonost.getbetter.shared.features.profiledetail.presentation.contract.FollowState
import com.velkonost.getbetter.shared.features.profiledetail.presentation.contract.FollowState.Companion.reverseState
import com.velkonost.getbetter.shared.features.profiledetail.presentation.contract.NotesUI
import com.velkonost.getbetter.shared.features.profiledetail.presentation.contract.ProfileDetailAction
import com.velkonost.getbetter.shared.features.profiledetail.presentation.contract.ProfileDetailEvent
import com.velkonost.getbetter.shared.features.profiledetail.presentation.contract.ProfileDetailViewState
import com.velkonost.getbetter.shared.features.userinfo.api.UserInfoRepository
import kotlinx.coroutines.Job

class ProfileDetailViewModel
internal constructor(
    private val notesRepository: NotesRepository,
    private val likesRepository: LikesRepository,
    private val followsRepository: FollowsRepository,
    private val userInfoRepository: UserInfoRepository,
) : BaseViewModel<ProfileDetailViewState, ProfileDetailAction, Nothing, ProfileDetailEvent>(
    initialState = ProfileDetailViewState()
) {

    private val _notesPagingConfig = PagingConfig()
    private val likesJobsMap: HashMap<Int, Job> = hashMapOf()

    override fun dispatch(action: ProfileDetailAction) = when (action) {
        is ProfileDetailAction.Load -> fetchUser(action.userId)
        is ProfileDetailAction.FollowClick -> obtainFollow()
        is ProfileDetailAction.NotesLoadNextPage -> fetchUserNotes()
        is ProfileDetailAction.NoteClick -> obtainNoteClick(action.value)
        is ProfileDetailAction.NoteLikeClick -> obtainNoteLikeClick(action.value)
    }

    private fun fetchUser(userId: String) {
        launchJob {
            userInfoRepository.fetchInfoAboutOtherUser(userId) collectAndProcess {
                isLoading {
                    val profileData = viewState.value.profileData.copy(isLoading = it)
                    emit(viewState.value.copy(profileData = profileData))
                }
                onSuccess { userInfo ->
                    userInfo?.let {
                        val profileData = viewState.value.profileData.copy(
                            userId = it.id,
                            userName = it.displayName ?: "",
                            experienceData = it.experienceData,
                            avatarUrl = it.avatarUrl
                        )
                        val followData = viewState.value.followData.copy(
                            state = if (it.isFollows) FollowState.Followed else FollowState.Unfollowed,
                            isLoading = false
                        )

                        emit(
                            viewState.value.copy(
                                profileData = profileData,
                                followData = followData,
                                notesData = NotesUI()
                            )
                        )

                        _notesPagingConfig.reset()
                        fetchUserNotes()
                    }
                }
            }
        }
    }

    private fun fetchUserNotes() {
        if (_notesPagingConfig.lastPageReached) return

        launchJob {
            notesRepository.fetchOtherUserNotes(
                userId = viewState.value.profileData.userId,
                page = _notesPagingConfig.page,
                pageSize = _notesPagingConfig.pageSize,
            ) collectAndProcess {
                isLoading {
                    val notesData = viewState.value.notesData.copy(isLoading = it)
                    emit(viewState.value.copy(notesData = notesData))
                }
                onSuccess { items ->
                    _notesPagingConfig.lastPageReached = items.isNullOrEmpty()
                    _notesPagingConfig.page++

                    items?.let {
                        val uiItems = viewState.value.notesData.items.plus(it)
                        val notesData = viewState.value.notesData.copy(items = uiItems)
                        emit(viewState.value.copy(notesData = notesData))
                    }
                }
            }
        }
    }

    private fun obtainFollow() {
        launchJob {
            val request =
                if (viewState.value.followData.state == FollowState.Followed) followsRepository.removeFollow(
                    followUserId = viewState.value.profileData.userId
                )
                else followsRepository.addFollow(followUserId = viewState.value.profileData.userId)

            request collectAndProcess {
                isLoading {
                    val followData = viewState.value.followData.copy(isLoading = it)
                    emit(viewState.value.copy(followData = followData))
                }
                onSuccess {
                    val newFollowState = viewState.value.followData.state.reverseState()
                    val followData = viewState.value.followData.copy(state = newFollowState)
                    emit(viewState.value.copy(followData = followData))
                }
            }
        }
    }

    private fun obtainNoteClick(value: Note) {
    }

    private fun obtainNoteLikeClick(value: Note) {
        if (likesJobsMap.containsKey(value.id)) return

        launchJob {
            val likeType = when (value.likesData.userLike) {
                LikeType.Positive -> LikeType.None
                else -> LikeType.Positive
            }

            likesRepository.addLike(
                entityType = EntityType.Note,
                entityId = value.id,
                likeType = likeType
            ) collectAndProcess {
                isLoading {
                    val itemLikesData = value.likesData.copy(isLikesLoading = true)

                    val indexOfChangedItem =
                        viewState.value.notesData.items.indexOfFirst { item -> item.id == value.id }
                    val allItems = viewState.value.notesData.items.toMutableList()

                    if (indexOfChangedItem != -1) {
                        allItems[indexOfChangedItem] = value.copy(
                            likesData = itemLikesData
                        )
                    }

                    val notesData = viewState.value.notesData.copy(items = allItems)
                    emit(viewState.value.copy(notesData = notesData))
                }
                onSuccess { entityLikes ->
                    entityLikes?.let {
                        val itemLikesData =
                            com.velkonost.getbetter.shared.core.model.likes.LikesData(
                                totalLikes = it.total,
                                userLike = it.userLikeType
                            )

                        val indexOfChangedItem =
                            viewState.value.notesData.items.indexOfFirst { item -> item.id == value.id }
                        val allItems = viewState.value.notesData.items.toMutableList()

                        if (indexOfChangedItem != -1) {
                            allItems[indexOfChangedItem] = value.copy(
                                likesData = itemLikesData
                            )
                        }

                        val notesData = viewState.value.notesData.copy(items = allItems)
                        emit(viewState.value.copy(notesData = notesData))
                    }
                }
            }
        }.also {
            likesJobsMap[value.id] = it
        }.invokeOnCompletion {
            likesJobsMap.remove(value.id)
        }
    }

}