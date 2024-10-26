package com.smtersoyoglu.navigationapp.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.smtersoyoglu.navigationapp.bottomnav.BottomNavBar
import com.smtersoyoglu.navigationapp.screens.LearnedWordsScreen
import com.smtersoyoglu.navigationapp.screens.WordDetailScreen
import com.smtersoyoglu.navigationapp.screens.WordGridScreen

@Composable
fun WordAppNav(modifier: Modifier) {
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination?.route

    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            if (currentDestination != Screen.WordDetailScreen.route) {
                BottomNavBar(navController = navController)
            }
        }
    ) { innerPadding ->
        Box(Modifier.padding(innerPadding)) {
            NavHost(
                navController,
                startDestination = Screen.WordGridScreen.route,
            ) {
                composable(
                    Screen.WordGridScreen.route,
                    enterTransition = ::slideInToRight,
                    exitTransition = ::slideOutToLeft
                ) {
                    WordGridScreen(navController)
                }

                composable(
                    Screen.LearnedWordsScreen.route

                ) {
                    LearnedWordsScreen(navController)
                }

                composable(
                    Screen.WordDetailScreen.route,
                    arguments = listOf(navArgument("wordId") { type = NavType.IntType }),
                    enterTransition = ::slideInToLeft,
                    exitTransition = ::slideOutToLeft,
                    popEnterTransition = ::slideInToRight,
                    popExitTransition = ::slideOutToRight
                ) { backStackEntry ->
                    val wordId = backStackEntry.arguments?.getInt("wordId")
                    wordId?.let {
                        WordDetailScreen(navController, wordId)
                    }
                }
            }
        }
    }
}

fun slideInToLeft(scope: AnimatedContentTransitionScope<NavBackStackEntry>): EnterTransition {
    return scope.slideIntoContainer(
        AnimatedContentTransitionScope.SlideDirection.Left,
        animationSpec = tween(300)
    )
}

fun slideInToRight(scope: AnimatedContentTransitionScope<NavBackStackEntry>): EnterTransition {
    return scope.slideIntoContainer(
        AnimatedContentTransitionScope.SlideDirection.Right,
        animationSpec = tween(300)
    )
}

fun slideOutToLeft(scope: AnimatedContentTransitionScope<NavBackStackEntry>): ExitTransition {
    return scope.slideOutOfContainer(
        AnimatedContentTransitionScope.SlideDirection.Left,
        animationSpec = tween(300)
    )
}

fun slideOutToRight(scope: AnimatedContentTransitionScope<NavBackStackEntry>): ExitTransition {
    return scope.slideOutOfContainer(
        AnimatedContentTransitionScope.SlideDirection.Right,
        animationSpec = tween(300)
    )
}

