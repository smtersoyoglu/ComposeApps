package com.smtersoyoglu.composecryptoapp.navigation

sealed class Screen(val route: String) {
    object CryptoListScreen : Screen("crypto_list_screen")
    object CryptoDetailScreen : Screen("crypto_detail_screen/{cryptoId}/{cryptoPrice}") {
        fun createRoute(cryptoId: String, cryptoPrice: String) =
            "crypto_detail_screen/$cryptoId/$cryptoPrice"
    }
}