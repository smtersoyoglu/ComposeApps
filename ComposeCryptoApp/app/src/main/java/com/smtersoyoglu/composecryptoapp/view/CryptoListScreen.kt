package com.smtersoyoglu.composecryptoapp.view

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.smtersoyoglu.composecryptoapp.model.CryptoListItem
import com.smtersoyoglu.composecryptoapp.navigation.Screen
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
            Text(
                text = "Crypto List",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                textAlign = TextAlign.Center,
                fontSize = 32.sp,
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

            Spacer(modifier = Modifier.height(8.dp))

            CryptoList(navController = navController)
        }
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    hint: String = "",
    onSearch: (String) -> Unit = {} //arama yapıldıgında bize bir kod blogu acar ve yapılan aramayı string olarak vermeye yarar.
) {
    var text by remember { -> mutableStateOf("") }

    var isHintDisplayed by remember { mutableStateOf(hint != "") }

    Box(modifier = modifier) {
        TextField(
            value = text,
            onValueChange = { // onValueChange kullanici birsey yazdiginda degisecegi icin
                text = it
                onSearch(it)
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color(0xFF1B1B2F), fontSize = 14.sp),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(Color.White, CircleShape)
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .onFocusChanged { //kullanici buraya tıklamayı bıraktıktan sonra nolsun diye kullanıyoruz, hint'in devreye girmesi(gosterilmesi) olayi
                    isHintDisplayed = it.isFocused != true && text.isEmpty()
                }
        )
        if (isHintDisplayed) {
            Text(
                text = hint,
                color = Color.Gray,
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 12.dp)
            )
        }
    }
}

@Composable
fun CryptoList( // Burada viewmodel'ı kullanıyoruz.
    navController: NavController,
    viewModel: CryptoListViewModel = hiltViewModel()
) {
    val cryptoList by remember { viewModel.cryptoList }
    val errorMessage by remember { viewModel.errorMessage }
    val isLoading by remember { viewModel.isLoading }

    CryptoListView(cryptos = cryptoList,navController = navController)

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        if(isLoading) {
            CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
        }
        if(errorMessage.isNotEmpty()) {
            RetryView(error = errorMessage) {
                viewModel.loadCryptos()
            }
        }
    }

}

@Composable
fun CryptoListView(cryptos: List<CryptoListItem>,navController: NavController) {
    LazyColumn(contentPadding = PaddingValues(5.dp)) {
        items(cryptos) { crypto ->
            CryptoRow(navController = navController,crypto = crypto)
        }
    }
}

@Composable
fun CryptoRow(navController: NavController, crypto: CryptoListItem) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .background(Color(0xFF1B1B2F)) // Liste arka planını ana ekranla aynı renk yaptım
        .clickable {
            navController.navigate(
                Screen.CryptoDetailScreen.createRoute(
                    crypto.currency,
                    crypto.price
                )
            )
        }.padding(12.dp)
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
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(3.dp)
            .background(Color(0xFF3A3A52)) // Ayırıcı çizgi
        )
    }
}

@Composable
fun RetryView( // Loading başarısız olursa bir Button ile verileri tekrardan yüklemek isteyebiliriz.
    error: String,
    onRetry: () -> Unit
) {
    Column {
        Text(error, color = Color.Red, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = { onRetry() },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Retry")
        }
    }
}
