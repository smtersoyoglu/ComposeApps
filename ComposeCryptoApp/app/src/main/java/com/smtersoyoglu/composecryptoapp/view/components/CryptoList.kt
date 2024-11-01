package com.smtersoyoglu.composecryptoapp.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.smtersoyoglu.composecryptoapp.model.CryptoListItem
import com.smtersoyoglu.composecryptoapp.navigation.Screen
import com.smtersoyoglu.composecryptoapp.viewmodel.CryptoListViewModel


@Composable
fun CryptoList( // Burada viewmodel'ı kullanıyoruz.
    navController: NavController,
    viewModel: CryptoListViewModel = hiltViewModel()
) {
    val cryptoList by remember { viewModel.cryptoList }
    val errorMessage by remember { viewModel.errorMessage }
    val isLoading by remember { viewModel.isLoading }

    CryptoListView(cryptos = cryptoList, navController = navController)

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        if (isLoading) {
            CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
        }
        if (errorMessage.isNotEmpty()) {
            RetryView(error = errorMessage) {
                viewModel.loadCryptos()
            }
        }
    }

}

@Composable
fun CryptoListView(cryptos: List<CryptoListItem>, navController: NavController) {
    LazyColumn(contentPadding = PaddingValues(5.dp)) {
        items(cryptos) { crypto ->
            CryptoRow(navController = navController, crypto = crypto)
        }
    }
}

@Composable
fun CryptoRow(navController: NavController, crypto: CryptoListItem) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF1B1B2F)) // Liste arka planını ana ekranla aynı renk yaptım
            .clickable {
                navController.navigate(
                    Screen.CryptoDetailScreen.createRoute(
                        crypto.currency,
                        crypto.price
                    )
                )
            }
            .padding(12.dp)
    ) {
        Text(
            text = crypto.currency,
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
            color = Color(0xFFF2E7FE),
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Text(
            text = crypto.price,
            style = TextStyle(fontSize = 16.sp),
            color = Color(0xFFB8C1EC)
        )
        Spacer(modifier = Modifier.height(5.dp))
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(3.dp)
                .background(Color(0xFF3A3A52)) // Ayırıcı çizgi
        )
    }
}
