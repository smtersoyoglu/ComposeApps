package com.smtersoyoglu.navigationapp.navigation

sealed class Screen(val route: String)  {
    data object WordGridScreen : Screen("wordGridScreen")
    data object WordDetailScreen : Screen("wordDetailScreen/{wordId}") {
        fun createRoute(wordId: Int) = "wordDetailScreen/$wordId"
    }
    data object LearnedWordsScreen : Screen(route = "learnedWordsScreen")
}