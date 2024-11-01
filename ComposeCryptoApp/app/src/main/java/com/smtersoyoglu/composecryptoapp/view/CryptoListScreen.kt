package com.smtersoyoglu.composecryptoapp.view


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.smtersoyoglu.composecryptoapp.view.components.CryptoList
import com.smtersoyoglu.composecryptoapp.view.components.SearchBar
import com.smtersoyoglu.composecryptoapp.viewmodel.CryptoListViewModel

@Composable
fun CryptoListScreen(
    navController: NavController,
    viewModel: CryptoListViewModel = hiltViewModel()
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF1B1B2F) // Arka plan rengini tüm ekranda aynı olacak şekilde ayarladım
    ) {
        Column {
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Crypto List",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                textAlign = TextAlign.Center,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFA29BFE) // Başlık rengi olarak morumsu bir ton
            )

            Spacer(modifier = Modifier.height(10.dp))

            SearchBar(
                hint = "Search...",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) { query -> // Search bar içinde oluşturduğumuz onSearch parametresi sayesinde bu bloğu kullanabiliyoruz.
                // search bar içinde viewmodelde yazdığımız arama algoritmasını çağırıyoruz
                viewModel.searchCryptoList(query)
            }

            Spacer(modifier = Modifier.height(10.dp))

            CryptoList(navController = navController)
        }
    }
}

