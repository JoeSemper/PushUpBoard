package com.joesemper.pushupboard.ui

import androidx.annotation.StringRes
import androidx.compose.animation.*
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.joesemper.pushupboard.R
import com.joesemper.pushupboard.ui.screens.home.HomeScreen
import com.joesemper.pushupboard.ui.screens.progress.ProgressScreen
import com.joesemper.pushupboard.ui.screens.select.ProgramSelectScreen
import com.joesemper.pushupboard.ui.screens.settings.SettingsScreen
import com.joesemper.pushupboard.ui.screens.workout.WorkoutScreen

const val HOME_ROUTE = "home"
const val PROGRESS_ROUTE = "progress"
const val SETTINGS_ROUTE = "settings"
const val WORKOUT_ROUTE = "workout/{workoutId}"
const val PROGRAM_SELECT_ROUTE = "select"

sealed class Screen(val route: String, @StringRes val labelRes: Int, val icon: ImageVector) {
    object Home : Screen(
        route = HOME_ROUTE,
        labelRes = R.string.home,
        icon = Icons.Default.Home
    )

    object Progress : Screen(
        route = PROGRESS_ROUTE,
        labelRes = R.string.progress,
        icon = Icons.Default.AccountCircle
    )

    object Settings : Screen(
        route = SETTINGS_ROUTE,
        labelRes = R.string.settings,
        icon = Icons.Default.Settings
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = HOME_ROUTE,
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val bottomBarVisibilityState = rememberSaveable { (mutableStateOf(true)) }

    when (navBackStackEntry?.destination?.route) {
        WORKOUT_ROUTE -> bottomBarVisibilityState.value = false
        PROGRAM_SELECT_ROUTE -> bottomBarVisibilityState.value = false
        else -> bottomBarVisibilityState.value = true
    }

    val bottomNavItems = listOf(
        Screen.Home,
        Screen.Progress,
        Screen.Settings
    )

    Scaffold(
        bottomBar = {
            AnimatedVisibility(
                visible = bottomBarVisibilityState.value,
                enter = slideInVertically { it },
                exit = slideOutVertically { it }
            ) {
                NavigationBar(
                    contentColor = MaterialTheme.colorScheme.primary,
                ) {
                    val currentDestination = navBackStackEntry?.destination

                    bottomNavItems.forEach { screen ->
                        NavigationBarItem(
                            icon = { Icon(imageVector = screen.icon, contentDescription = null) },
                            label = { Text(text = stringResource(id = screen.labelRes)) },
                            selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            })
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            modifier = modifier.padding(innerPadding),
            navController = navController,
            startDestination = startDestination
        ) {
            composable(
                route = Screen.Home.route
            ) {
                HomeScreen(navController)
            }

            composable(
                route = Screen.Progress.route
            ) {
                ProgressScreen(navController)
            }

            composable(
                route = Screen.Settings.route
            ) {
                SettingsScreen(navController)
            }

            composable(
                route = WORKOUT_ROUTE,
                arguments = listOf(navArgument("workoutId") { type = NavType.IntType })
            ) {
                WorkoutScreen(navController)
            }

            composable(
                route = PROGRAM_SELECT_ROUTE
            ) {
                ProgramSelectScreen(navController)
            }

        }
    }
}