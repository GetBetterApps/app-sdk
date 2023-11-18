package com.velkonost.getbetter.android.features.profiledetail

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.velkonost.getbetter.android.features.profile.components.ProfileHeader
import com.velkonost.getbetter.core.compose.components.Loader
import com.velkonost.getbetter.core.compose.components.experience.LevelBlock
import com.velkonost.getbetter.shared.features.profiledetail.presentation.ProfileDetailViewModel
import com.velkonost.getbetter.shared.features.profiledetail.presentation.contract.ProfileDetailAction
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
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
                    Column(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .verticalScroll(rememberScrollState())
                            .padding(bottom = 140.dp)
                    ) {
                        ProfileHeader(
                            userName = state.profileData.userName,
                            isLoading = state.profileData.isLoading,
                            showSettings = false,
                            avatarBytes = state.profileData.avatarBytes,
                            onAvatarClick = {},
                            onSettingsClick = {}
                        )

                        AnimatedVisibility(visible = state.profileData.experienceData != null) {
                            state.profileData.experienceData?.let {
                                LevelBlock(experienceData = it, isOwn = false)
                            }
                        }
                    }
                }
            }
        }
    ) {}

    LaunchedEffect(userId) {
        viewModel.dispatch(ProfileDetailAction.Load(userId))
    }
}