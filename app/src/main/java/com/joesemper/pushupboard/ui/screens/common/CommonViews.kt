package com.joesemper.pushupboard.ui.screens.common

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.joesemper.pushupboard.ui.theme.GreenColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTopAppBar(
    modifier: Modifier = Modifier,
    title: String = ""
) {

    TopAppBar(
        modifier = modifier.fillMaxWidth(),
        title = {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.labelSmall
            )}
    )
}

@Composable
fun LoadingView(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(64.dp),
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun PagerIndicator(
    modifier: Modifier = Modifier,
    pagesCount: Int,
    currentPage: Int
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pagesCount) { iteration ->
            val color =
                animateColorAsState(
                    targetValue = when {
                        (currentPage == iteration) -> MaterialTheme.colorScheme.secondary
                        (currentPage > iteration) -> GreenColor
                        else -> MaterialTheme.colorScheme.secondary
                    }, label = ""
                )

            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .clip(CircleShape)
                    .background(color.value)
                    .size(8.dp)

            )
        }
    }
}

