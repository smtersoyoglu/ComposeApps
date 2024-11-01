package com.smtersoyoglu.composecryptoapp.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.smtersoyoglu.composecryptoapp.model.Crypto
import com.smtersoyoglu.composecryptoapp.util.Resource
import com.smtersoyoglu.composecryptoapp.viewmodel.CryptoDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CryptoDetailScreen(
    id: String,
    price: String,
    navController: NavController,
    viewModel: CryptoDetailViewModel = hiltViewModel()
) {

    /*
    Bu yöntemde sürekli recompose yapılarak görüntüyü yeniden yüklemeye çalışır ve veriler tekrar tekrar çekilmeye çalışır. (devamlı istek atıyor)
    //Step 1 -> Wrong (yanlis yontem)
       val scope = rememberCoroutineScope()
       //Stateless
       //var cryptoItem : Resource<Crypto> = Resource.Loading()
       //Stateful
       var cryptoItem by remember { mutableStateOf<Resource<Crypto>>(Resource.Loading())}
         scope.launch {
             cryptoItem = viewModel.getCrypto(id)
             println(cryptoItem.data)
         }
      */


    /*S tep 2 -> Better
    //Stateless
       //var cryptoItem : Resource<Crypto> = Resource.Loading()
       //Stateful
       var cryptoItem by remember { mutableStateOf<Resource<Crypto>>(Resource.Loading())}
         LaunchedEffect(key1 = Unit) {
             cryptoItem = viewModel.getCrypto(id)
             //println(cryptoItem.data)
         }
         */

    //Step 3 -> Best


    val cryptoItem by produceState<Resource<Crypto>>(initialValue = Resource.Loading()) {
        value = viewModel.getCrypto(id)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF1B1B2F),
                    titleContentColor = Color(0xFFF2E7FE),
                ),
                title = { Text("Crypto Detail", fontSize = 32.sp ) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color(0xFFF2E7FE)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFF1B1B2F))
                .padding(paddingValues),
            contentAlignment = Alignment.Center,
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                when (cryptoItem) {
                    is Resource.Success -> {
                        val selectedCrypto = cryptoItem.data!![0]
                        Text(
                            text = selectedCrypto.name,
                            fontSize = 40.sp,
                            modifier = Modifier.padding(2.dp),
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFF2E7FE),
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        AsyncImage(
                            model = selectedCrypto.logo_url,
                            contentDescription = selectedCrypto.name,
                            modifier = Modifier
                                .padding(16.dp)
                                .size(300.dp)
                                .clip(CircleShape)
                                .border(2.dp, Color.Gray, CircleShape)
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = price,
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.padding(2.dp),
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFB8C1EC),
                            textAlign = TextAlign.Center
                        )
                    }

                    is Resource.Error -> {
                        Text(cryptoItem.message!!)
                    }

                    is Resource.Loading -> {
                        CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                    }
                }
            }
        }
    }
}