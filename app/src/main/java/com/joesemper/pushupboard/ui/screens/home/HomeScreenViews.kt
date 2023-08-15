package com.joesemper.pushupboard.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = state.date,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "Workout",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }


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
                    tint = MaterialTheme.colorScheme.secondary
                )

            }
        }
    }
}

@Composable
fun ProgressListItem(
    modifier: Modifier = Modifier,
    progress: ProgramProgress
) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        item {
            Card(
                modifier = Modifier
                    .size(128.dp)
                    .padding(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    contentColor = MaterialTheme.colorScheme.onTertiaryContainer
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 8.dp, horizontal = 4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Spacer(modifier = Modifier.size(0.dp))
                    Text(
                        text = "${progress.percentProgress}%",
                        style = MaterialTheme.typography.displaySmall
                    )
                    Text(
                        text = "Progress",
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }

        item {
            Card(
                modifier = Modifier
                    .size(128.dp)
                    .padding(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    contentColor = MaterialTheme.colorScheme.onTertiaryContainer
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 8.dp, horizontal = 4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Icon(imageVector = Icons.Outlined.ThumbUp, contentDescription = null)
                    Text(
                        text = "1234",
                        style = MaterialTheme.typography.labelLarge
                    )

                    Text(
                        text = "Total push ups",
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }

        item {
            Card(
                modifier = Modifier
                    .size(128.dp)
                    .padding(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    contentColor = MaterialTheme.colorScheme.onTertiaryContainer
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 8.dp, horizontal = 4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Icon(imageVector = Icons.Outlined.FavoriteBorder, contentDescription = null)
                    Text(
                        text = "1234",
                        style = MaterialTheme.typography.labelLarge
                    )
                    Text(
                        text = "Calories",
                        style = MaterialTheme.typography.labelSmall
                    )
                }

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
        shape = RoundedCornerShape(32.dp),
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