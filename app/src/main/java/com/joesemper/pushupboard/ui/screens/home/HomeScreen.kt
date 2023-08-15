package com.joesemper.pushupboard.ui.screens.home

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.joesemper.pushupboard.ui.PROGRAM_SELECT_ROUTE
import com.joesemper.pushupboard.ui.screens.common.LoadingView
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController: NavController
) {

    val viewModel: HomeViewModel = getViewModel()
    val state = viewModel.homeState

    Scaffold(
        topBar = {
            HomeScreenTopBar(
                state = viewModel.homeState.topBarState,
                onIconClick = { navController.navigate(PROGRAM_SELECT_ROUTE) }
            )
        },
    ) { padding ->

        if (state.isLoading) {

            LoadingView(modifier = Modifier.fillMaxSize())

        } else {

            HomeScreenContent(
                modifier = Modifier.padding(padding),
                state = state,
                onWorkoutItemClick = { navController.navigate("workout/${it}") },
                onClick = viewModel::updateDates
            )

        }
    }
}

@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    state: HomeScreenState,
    onWorkoutItemClick: (Int) -> Unit,
    onClick: () -> Unit
) {
    AnimatedVisibility(visible = !state.isLoading) {

        LazyColumn(
            modifier = modifier,
        ) {

            item {
                ProgressListItem(progress = state.programProgress)
            }

            item {
                Button(onClick = onClick) {
                    Text(text = "Update")
                }
            }

            items(count = state.workouts.size) { columnId ->
                WorkoutListItem(
                    modifier = Modifier
                        .clickable { onWorkoutItemClick(state.workouts[columnId].workoutId) }
                        .padding(vertical = 8.dp, horizontal = 16.dp),
                    state = state.workouts[columnId]
                )
            }

        }

    }
}
