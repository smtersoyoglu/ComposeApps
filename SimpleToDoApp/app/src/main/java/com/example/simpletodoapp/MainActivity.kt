package com.example.simpletodoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.simpletodoapp.ui.theme.SimpleToDoAppTheme
import com.example.simpletodoapp.viewmodel.TodoViewModel

//Scaffold: Uygulamanızın ana yapısını ve düzenini oluşturmak için kullanılır. Özellikle AppBar, BottomNavigationBar, FloatingActionButton gibi bileşenleri kolayca eklemek ve yerleştirmek için uygundur.
//Surface: Daha basit bir yapı elemanıdır. Belirli bir bileşenin arka plan rengini, şekillendirmesini ve gölgesini belirlemek için kullanılır.
class MainActivity : ComponentActivity() {
    // onCreate: Aktivite başlatıldığında çağrılan metot.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // ViewModelProvider ile TodoViewModel sınıfından bir ViewModel örneği alıyoruz.
        // ViewModel'i oluşturuyoruz. ViewModel, UI ile ilgili verileri yönetir.
        val todoViewModel = ViewModelProvider(this)[TodoViewModel::class.java]

        // Kenardan kenara (edge-to-edge) kullanıcı arayüzü desteğini etkinleştiriyoruz.
        // Tam ekran modunu etkinleştiriyoruz.
        enableEdgeToEdge()
        // setContent fonksiyonu ile Compose UI içeriklerini ayarlıyoruz.
        setContent {


            // SimpleToDoAppTheme: Uygulamanın temasını belirleyen fonksiyon.
            SimpleToDoAppTheme {
                // Surface: Arka plan rengi ve dolgu için kullanılan basit bir yapı elemanı.
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background) {
                    // TodoListPage bileşenini görüntülüyoruz ve ViewModel'i ona geçiriyoruz.
                    TodoListPage(todoViewModel)

                }
            }
        }
    }
}
