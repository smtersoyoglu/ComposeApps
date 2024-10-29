package com.smtersoyoglu.composecryptoapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.smtersoyoglu.composecryptoapp.view.CryptoDetailScreen
import com.smtersoyoglu.composecryptoapp.view.CryptoListScreen

@Composable
fun CryptoNavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.CryptoListScreen.route
    ) {
        composable(Screen.CryptoListScreen.route) {
            // CryptoListScreen
            CryptoListScreen(navController = navController)
        }
        composable(Screen.CryptoDetailScreen.route, arguments = listOf(
            navArgument("cryptoId") {
                type = NavType.StringType
            },
            navArgument("cryptoPrice") {
                type = NavType.StringType
            }
        )) { backStackEntry->
            val cryptoId = remember{
                backStackEntry.arguments?.getString("cryptoId")
            }
            val cryptoPrice = remember {
                backStackEntry.arguments?.getString("cryptoPrice")
            }
            //CryptoDetailScreen
            CryptoDetailScreen(
                id = cryptoId ?: "",
                price = cryptoPrice ?: "",
                navController = navController
            )


        }
    }
}