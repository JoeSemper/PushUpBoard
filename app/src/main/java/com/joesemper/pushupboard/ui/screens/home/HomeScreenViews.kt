package com.joesemper.pushupboard.ui.screens.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.joesemper.pushupboard.domain.entity.WorkoutWithMuscleGroups

@Composable
fun WorkoutListItem(
    modifier: Modifier = Modifier,
    state: WorkoutWithMuscleGroups
) {
    Card(
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier.size(64.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "${state.dayInProgram}",
                        style = MaterialTheme.typography.labelSmall,
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "21.06.2032",
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Row() {
                        state.muscleGroups.forEach {
                            RoundedIcon(
                                modifier = Modifier.padding(horizontal = 4.dp),
                                iconRes = it.muscleGroupResId,
                            )
                        }
                    }

                }

                Icon(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .size(32.dp),
                    imageVector = if (state.isComplete) Icons.Default.Done else Icons.Default.Lock,
                    contentDescription = null,
                )

            }

//            Divider(
//                modifier = Modifier
//                    .padding(start = 64.dp)
//                    .fillMaxWidth()
//            )
        }
    }
}

@Composable
fun ProgressListItem(
    modifier: Modifier = Modifier,
    progress: ProgramProgress
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp),
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.6f),
            ) {
            }
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.4f),
            ) {
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            elevation = CardDefaults.cardElevation()
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Push up beginner")
                LinearProgressIndicator(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    progress = (progress.workoutsDone.toFloat() / progress.totalWorkouts.toFloat())
                )
            }
        }

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenTopBar(
    modifier: Modifier = Modifier,
    state: HomeTopBarState,
    onIconClick: () -> Unit
) {

//    val backgroundColor = animateColorAsState(
//        targetValue = if (state.reverseColors) {
//            MaterialTheme.colorScheme.background
//        } else {
//            MaterialTheme.colorScheme.primary
//        }, label = ""
//    )
//
//    val contentColor = animateColorAsState(
//        targetValue = if (state.reverseColors) {
//            MaterialTheme.colorScheme.onBackground
//        } else {
//            MaterialTheme.colorScheme.onPrimary
//        }, label = ""
//    )

//    val elevation = animateDpAsState(
//        targetValue = if (state.applyElevation) 8.dp else 0.dp, label = ""
//    )

    TopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = state.title,
                style = MaterialTheme.typography.titleMedium,
            )
        },
        actions = {
            IconButton(
                modifier = Modifier.size(32.dp),
                onClick = onIconClick
            ) {
                Icon(
                    modifier = Modifier.size(32.dp),
                    imageVector = Icons.Default.List,
                    contentDescription = null,
                )
            }
        },
    )
}

@Composable
fun RoundedIcon(
    modifier: Modifier = Modifier,
    iconRes: Int,
) {
    Surface(
        modifier = modifier.size(32.dp),
        color = MaterialTheme.colorScheme.secondary,
        shape = RoundedCornerShape(32.dp),
        border = BorderStroke(width = 0.5.dp, color = MaterialTheme.colorScheme.primary)
    ) {
        Icon(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxSize(),
            painter = painterResource(id = iconRes),
            contentDescription = null
        )
    }
}