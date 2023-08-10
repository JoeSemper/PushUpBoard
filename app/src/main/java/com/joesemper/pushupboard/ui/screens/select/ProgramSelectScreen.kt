package com.joesemper.pushupboard.ui.screens.select

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.joesemper.pushupboard.ui.HOME_ROUTE
import com.joesemper.pushupboard.ui.screens.common.LoadingView
import org.koin.androidx.compose.getViewModel

@Composable
fun ProgramSelectScreen(
    navController: NavController
) {
    val viewModel: ProgramSelectViewModel = getViewModel()
    val state = viewModel.uiState

    LaunchedEffect(key1 = state.isSelected) {
        if (viewModel.uiState.isSelected) {
            navController.navigate(HOME_ROUTE)
        }
    }

    AnimatedVisibility(
        visible = !state.isLoading,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            state.programs.forEach {
                Text(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .clickable { viewModel.onSelectProgram(it.programId) },
                    text = it.programName
                )
            }
        }
    }




}

