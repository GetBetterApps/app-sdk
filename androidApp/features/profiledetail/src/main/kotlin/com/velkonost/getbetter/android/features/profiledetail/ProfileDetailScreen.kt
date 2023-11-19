package com.velkonost.getbetter.android.features.profiledetail

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.velkonost.getbetter.android.features.profile.components.ProfileHeader
import com.velkonost.getbetter.android.features.social.components.FeedNoteItem
import com.velkonost.getbetter.core.compose.components.AppButton
import com.velkonost.getbetter.core.compose.components.Loader
import com.velkonost.getbetter.core.compose.components.experience.LevelBlock
import com.velkonost.getbetter.core.compose.extensions.OnBottomReached
import com.velkonost.getbetter.shared.features.profiledetail.presentation.ProfileDetailViewModel
import com.velkonost.getbetter.shared.features.profiledetail.presentation.contract.FollowState
import com.velkonost.getbetter.shared.features.profiledetail.presentation.contract.ProfileDetailAction
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.stringResource
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProfileDetailScreen(
    modifier: Modifier = Modifier,
    userId: String?,
    viewModel: ProfileDetailViewModel = koinViewModel(),
    modalSheetState: ModalBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true,
    ),
) {
    if (userId == null) return

    val scope = rememberCoroutineScope()
    val state by viewModel.viewState.collectAsStateWithLifecycle()
    val listState = rememberLazyListState()

    BackHandler {
        scope.launch {
            modalSheetState.hide()
        }
    }

    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        sheetBackgroundColor = colorResource(resource = SharedR.colors.main_background),
        sheetContent = {
            if (state.profileData.isLoading) {
                Box(
                    modifier = modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.9f)
                        .padding(20.dp)
                ) { Loader(modifier = modifier.align(Alignment.Center)) }
            } else {
                Box(
                    modifier = modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.9f)
                ) {
                    LazyColumn(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        contentPadding = PaddingValues(bottom = 140.dp),
                        state = listState
                    ) {

                        item {
                            ProfileHeader(
                                userName = state.profileData.userName,
                                isLoading = state.profileData.isLoading,
                                showSettings = false,
                                avatarBytes = state.profileData.avatarBytes,
                                onAvatarClick = {},
                                onSettingsClick = {}
                            )
                        }

                        item {
                            state.profileData.experienceData?.let {
                                LevelBlock(experienceData = it, isOwn = false)
                            }
                        }

                        if (state.notesData.isLoading && state.notesData.items.isEmpty()) {
                            item {
                                Box(
                                    modifier = modifier
                                        .fillMaxWidth()
                                        .padding(20.dp)
                                ) { Loader(modifier = modifier.align(Alignment.Center)) }
                            }
                        } else {
                            item {
                                Text(
                                    modifier = modifier
                                        .padding(top = 24.dp)
                                        .fillMaxWidth(0.8f),
                                    text = stringResource(resource = SharedR.strings.diary_notes_title),
                                    color = colorResource(resource = SharedR.colors.text_primary),
                                    style = MaterialTheme.typography.headlineSmall
                                )
                            }
                            items(
                                state.notesData.items,
                                key = { it.id },
                                contentType = { it.noteType }
                            ) { item ->
                                FeedNoteItem(
                                    paddings = PaddingValues(0.dp),
                                    item = item,
                                    onClick = {
                                        viewModel.dispatch(ProfileDetailAction.NoteClick(it))
                                    },
                                    onLikeClick = {
                                        viewModel.dispatch(ProfileDetailAction.NoteLikeClick(it))
                                    }
                                )
                            }

                            if (state.notesData.isLoading) {
                                item {
                                    Box(modifier = modifier.fillMaxSize()) {
                                        Loader(modifier = modifier.align(Alignment.Center))
                                    }
                                }
                            }
                        }
                    }

                    Column(
                        modifier = modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = modifier.weight(1f))

                        Box(
                            modifier = modifier
                                .height(30.dp)
                                .fillMaxWidth()
                                .background(
                                    brush = Brush.verticalGradient(
                                        colors = listOf(
                                            Color.Transparent,
                                            colorResource(resource = SharedR.colors.main_background)
                                                .copy(alpha = 0.7f),
                                            colorResource(resource = SharedR.colors.main_background),
                                        ),
                                    ),
                                )
                        )

                        Box(
                            modifier = modifier
                                .fillMaxWidth()
                                .background(
                                    color = colorResource(resource = SharedR.colors.main_background)
                                )
                        ) {
                            AppButton(
                                modifier = modifier
                                    .align(Alignment.Center)
                                    .padding(bottom = 70.dp),
                                labelText = stringResource(
                                    resource =
                                    if (state.followData.state == FollowState.Followed)
                                        SharedR.strings.unfollow_title
                                    else SharedR.strings.follow_title
                                ),
                                isLoading = state.followData.isLoading,
                                onClick = {
                                    viewModel.dispatch(ProfileDetailAction.FollowClick)
                                }
                            )
                        }
                    }

                }
            }
        }
    ) {}

    listState.OnBottomReached(
        buffer = state.notesData.loadMorePrefetch,
        isLoading = state.notesData.isLoading,
        onLoadMore = {
            viewModel.dispatch(ProfileDetailAction.NotesLoadNextPage)
        }
    )

    LaunchedEffect(userId) {
        viewModel.dispatch(ProfileDetailAction.Load(userId))
    }
}