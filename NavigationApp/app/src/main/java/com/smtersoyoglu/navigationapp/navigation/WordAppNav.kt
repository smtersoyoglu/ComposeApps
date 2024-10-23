package com.smtersoyoglu.navigationapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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

    NavHost(navController, startDestination = "wordGridScreen") {
        composable("wordGridScreen") {
            WordGridScreen(navController)
        }
        composable("wordDetailScreen/{wordId}", arguments = listOf(navArgument("wordId") { type = NavType.IntType })
        ) { backStackEntry ->
            val wordId = backStackEntry.arguments?.getInt("wordId")
            wordId?.let {
                WordDetailScreen(navController,wordId)
            }
        }
    }
}