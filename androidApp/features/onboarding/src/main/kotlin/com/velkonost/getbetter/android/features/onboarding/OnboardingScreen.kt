package com.velkonost.getbetter.android.features.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.velkonost.getbetter.core.compose.components.AppButton
import com.velkonost.getbetter.shared.features.onboarding.presentation.OnboardingViewModel
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    viewModel: OnboardingViewModel
) {

    val state by viewModel.viewState.collectAsStateWithLifecycle()

    Box(modifier = modifier.fillMaxSize()) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Spacer(modifier.height(32.dp))

            Image(
                modifier = modifier
                    .shadow(
                        elevation = 8.dp,
                        shape = MaterialTheme.shapes.medium,
                    )
                    .fillMaxHeight(0.6f)
                    .clip(MaterialTheme.shapes.medium),
                painter = painterResource(imageResource = SharedR.images.ic_onboarding),
                contentDescription = null
            )

            Spacer(modifier.weight(1f))
            Text(
                modifier = modifier
                    .padding(bottom = 32.dp),
                text = stringResource(resource = SharedR.strings.onboarding_step_1),
                style = MaterialTheme.typography.titleMedium,
                color = colorResource(resource = SharedR.colors.text_title)
            )

            AppButton(
                labelText = stringResource(resource = SharedR.strings.continue_btn),
                isLoading = false,
                onClick = {

                }
            )
            Spacer(modifier = modifier.height(56.dp))
        }

    }

}