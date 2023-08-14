package com.joesemper.pushupboard.ui.screens.workout

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutScreenTopBar(
    modifier: Modifier = Modifier,
    text: String = "",
    isWorkoutComplete: Boolean
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = text,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.labelSmall
            )
        },
        actions = {
            Icon(
                modifier = Modifier.size(32.dp),
                imageVector = if (isWorkoutComplete) Icons.Default.Done else Icons.Default.Lock,
                contentDescription = null
            )
        }

    )
}