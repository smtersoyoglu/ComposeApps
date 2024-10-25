package com.smtersoyoglu.navigationapp.bottomnav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavScreen(val route: String, val icon: ImageVector, val title: String) {
    data object WordGridScreen : BottomNavScreen("wordGridScreen", Icons.Filled.Home, "Home")
    data object LearnedWords : BottomNavScreen("learnedWordsScreen", Icons.Filled.DateRange, "Learned Words")

}