package com.smtersoyoglu.composecryptoapp.viewmodel

import androidx.lifecycle.ViewModel
import com.smtersoyoglu.composecryptoapp.model.Crypto
import com.smtersoyoglu.composecryptoapp.repo.CryptoRepository
import com.smtersoyoglu.composecryptoapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CryptoDetailViewModel @Inject constructor(
    private val repository: CryptoRepository
) : ViewModel() {

    suspend fun getCrypto(id: String): Resource<Crypto> {
        return repository.getCrypto(id)
    }

}