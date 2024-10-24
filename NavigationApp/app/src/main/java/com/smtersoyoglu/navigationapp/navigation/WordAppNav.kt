package com.smtersoyoglu.navigationapp.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.smtersoyoglu.navigationapp.components.WordDetailScreen
import com.smtersoyoglu.navigationapp.screens.WordGridScreen

@Composable
fun WordAppNav(modifier: Modifier) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = Screen.WordGridScreen.route) {
        composable(
            Screen.WordGridScreen.route,
            enterTransition = ::slideInToRight,
            exitTransition = ::slideOutToLeft
        ) {
            WordGridScreen(navController)
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

