package com.smtersoyoglu.composecryptoapp.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smtersoyoglu.composecryptoapp.model.CryptoListItem
import com.smtersoyoglu.composecryptoapp.repo.CryptoRepository
import com.smtersoyoglu.composecryptoapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoListViewModel @Inject constructor(
    private val repository: CryptoRepository
) : ViewModel() {
    // compose da livedata'lar yerine mutablestate'leri kullanabiliriz.
    var cryptoList = mutableStateOf<List<CryptoListItem>>(listOf())
    var errorMessage = mutableStateOf("")
    var isLoading = mutableStateOf(false)
    // Resource da ki Success'de -> cryptoList, Error'da -> errorMessage, Loading'de -> isLoading dönecek.

    // Verileri indirdikten sonra kullanıcı arama textini silerse yeniden verileri yüklememek için listeyi başka değişkende tutuyoruz.
    private var initialCryptoList = listOf<CryptoListItem>()
    private var isSearchStarting = true

    init {
        loadCryptos()
    }

    fun loadCryptos() {
        viewModelScope.launch {
            isLoading.value = true
            when (val result = repository.getCryptoList()) {
                is Resource.Success -> {
                    val cryptoItems = result.data!!.mapIndexed { _, cryptoListItem ->
                        CryptoListItem(cryptoListItem.currency,cryptoListItem.price)
                    }

                    cryptoList.value += cryptoItems
                    errorMessage.value = ""
                    isLoading.value = false
                }

                is Resource.Error -> {
                    errorMessage.value = result.message!!
                    isLoading.value = false
                }

                is Resource.Loading -> {
                    isLoading.value = true
                }
            }

        }
    }


    fun searchCryptoList(query: String) {
        val listToSearch = if (isSearchStarting) {
            cryptoList.value  //arama başladıysa internetten çektiğimiz verilerden gösteriyoruz.
        } else {
            initialCryptoList // arama işlemi bittiyse listeyi kaydettiğimiz yerden tekrar çekiyoruz(internetten indirmiyoruz)
        }

        // Arama islemleri
        viewModelScope.launch(Dispatchers.Default) {
            if (query.isEmpty()) {
                //arama yapıldıktan sonra edittext içi silinmişse bu blok çalışır.
                cryptoList.value = initialCryptoList
                isSearchStarting = true
                return@launch
            }

            val results = listToSearch.filter {
                // arama yaptığımız karakterlerle listede eşleşen varsa filtreleme yapıyoruz.
                it.currency.contains(query.trim(), ignoreCase = true)
            }
            if(isSearchStarting) { // bir arama başlatıldıysa bu blok çalışıyor
                //internetten çektiğimiz bütün listeyi başka bir değişkene atıyoruz
                initialCryptoList = cryptoList.value
                isSearchStarting = false
            }
            // arama yapilanlarin gosterilmesi
            cryptoList.value = results

        }


    }


}