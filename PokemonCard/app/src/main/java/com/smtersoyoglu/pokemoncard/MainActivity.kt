package com.smtersoyoglu.pokemoncard

import android.content.res.Resources.Theme
import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.smtersoyoglu.pokemoncard.ui.theme.PokemonCardTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Uygulamanın tam ekran olmasını sağlar.
        enableEdgeToEdge()
        // Ekranın içeriğini ayarlamak için kullanılır.
        setContent {
            // Uygulamanın genel teması burada uygulanır.
            PokemonCardTheme {
                // Uygulama düzenini sağlayan Scaffold bileşeni kullanılır.
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize() // Ekranın tamamını kaplamasını sağlar.
                ) { innerPadding ->
                    // Elemanları dikeyde hizalamak için Column bileşeni kullanılır.
                    Column(
                        modifier = Modifier
                            .padding(innerPadding) // İçerideki elemanların kenarlardan boşluk bırakmasını sağlar.
                            .background(
                                // Arka plan için bir geçişli (gradient) renk ekler.
                                Brush.linearGradient(
                                    colors = listOf(
                                        Color(0xFF78C850),  // Yeşil
                                        Color(0xFFA7DB8D),  // Açık Yeşil
                                        Color(0xFF48D1CC),  // Mavi
                                        Color(0xFFFF7F50),  // Turuncu
                                        Color(0xFFFFD700),  // Sarı
                                        Color(0xFFCD5C5C),  // Koyu Kırmızı
                                        Color(0xFF4682B4),  // Mavi
                                        Color(0xFF2F4F4F),  // Koyu Gri
                                        Color(0xFF87CEEB),  // Açık Mavi
                                        Color(0xFF000080),  // Koyu Mavi
                                        Color(0xFF8B4513),  // Kahverengi
                                        Color(0xFFF4A460),  // Açık Kahverengi
                                        Color(0xFFFFFACD)   // Krem
                                    )
                                )
                            )
                    ) {
                        // Üstte biraz boşluk bırakmak için Spacer bileşeni kullanılır.
                        Spacer(modifier = Modifier.height(8.dp))

                        // Başlık olarak gösterilecek bir resim ekler.
                        Image(
                            painter = painterResource(id = R.drawable.pokemontext),
                            contentDescription = "Pokemon Header", // Erişilebilirlik için açıklama
                            modifier = Modifier
                                .fillMaxWidth() // Genişliği tamamen kaplar.
                                .height(120.dp) // Yüksekliği 120 dp yapar.
                        )

                        // Pokémon verilerini almak için bir liste oluşturur.
                        val pokemonList = PokemonRepository.getPokemonList()
                        // Pokémon listesini görüntülemek için bir grid (ızgara) bileşeni çağırır.
                        PokemonGrid(pokemonList = pokemonList)
                    }
                }
            }
        }
    }
}

// Pokémon verilerini grid (ızgara) şeklinde göstermek için kullanılan bileşen.
@Composable
fun PokemonGrid(pokemonList: List<Pokemon>) {
    // Dikey kaydırılabilir bir ızgara oluşturur.
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // İki sütunlu bir grid oluşturur.
        modifier = Modifier.fillMaxSize(), // Ekranın tamamını kaplamasını sağlar.
        contentPadding = PaddingValues(16.dp), // İçerideki elemanların kenarlardan boşluk bırakmasını sağlar.
        horizontalArrangement = Arrangement.spacedBy(16.dp), // Elemanlar arasında yatayda boşluk bırakır.
        verticalArrangement = Arrangement.spacedBy(16.dp) // Elemanlar arasında dikeyde boşluk bırakır.
    ) {
        // Her Pokémon için bir ızgara elemanı oluşturur.
        items(pokemonList) { pokemon ->
            // Animasyonlu bir renk için değişken tanımlar.
            val animatedColor = remember { Animatable(Color.Transparent) }
            // Renk animasyonunu başlatır.
            LaunchedEffect(Unit) {
                while (true) {
                    animatedColor.animateTo(
                        targetValue = Color(0xFFF0E68C), // Açık turuncu renk
                        animationSpec = tween(durationMillis = 1500) // 1.5 saniyelik animasyon
                    )
                    delay(1500)
                    animatedColor.animateTo(
                        targetValue = Color.Transparent, // Şeffaf renk
                        animationSpec = tween(durationMillis = 1500)
                    )
                    delay(1500)
                }
            }
            // Pokémon resmi ve adı için kart bileşenini çağırır.
            ImageCard(
                painter = painterResource(id = pokemon.imageResId),
                contentDescription = pokemon.name,
                title = pokemon.name,
                backgroundColor = animatedColor.value // Animasyonlu arka plan rengi burada uygulanır.
            )
        }
    }
}

@Composable
fun ImageCard(
    painter: Painter,
    contentDescription: String,
    title: String,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    // Kart bileşeni, görsel ve metin içerir.
    Card(
        modifier = Modifier
            .fillMaxWidth() // Kart genişliğini tamamen kaplar.
            .padding(8.dp), // Kenarlardan boşluk bırakır.
        shape = RoundedCornerShape(15.dp), // Köşeleri yuvarlak yapar.
        elevation = CardDefaults.cardElevation(5.dp) // Kartın gölgesini ayarlar.
    ) {
        // Kutunun tamamını kaplayan bir düzen oluşturur.
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2f / 3f) // Kartın boyut oranını belirler.
                .background(backgroundColor) // Arka plan rengi uygulanır.
        ) {
            // Resim kutusunun içini kaplar.
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                // Pokémon resmini görüntüler.
                Image(
                    painter = painter,
                    contentDescription = contentDescription,
                    contentScale = ContentScale.Fit, // Resmi kutunun içine sığacak şekilde ayarlar.
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.Center) // Resmi ortalar.
                )
            }
            // Kartın alt kısmına doğru karartma efekti ekler.
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent, // Üst kısım şeffaf
                                Color.Black // Alt kısım siyah
                            ),
                            startY = 300f // Karartmanın başlangıç noktası
                        )
                    )
            )
            // Metni kartın alt kısmında ortalar.
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp), // Metnin kenarlarından boşluk bırakır.
                contentAlignment = Alignment.BottomCenter // Metni alt ortada hizalar.
            ) {
                // Pokémon adını gösterir.
                Text(
                    text = title,
                    style = TextStyle(color = Color.White, fontSize = 21.sp) // Metin stilini ayarlar.
                )
            }
        }
    }
}
