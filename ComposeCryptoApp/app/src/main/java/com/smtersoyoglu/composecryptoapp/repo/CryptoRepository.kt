package com.smtersoyoglu.composecryptoapp.repo

import com.smtersoyoglu.composecryptoapp.model.Crypto
import com.smtersoyoglu.composecryptoapp.model.CryptoList
import com.smtersoyoglu.composecryptoapp.service.CryptoAPI
import com.smtersoyoglu.composecryptoapp.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class CryptoRepository @Inject constructor(
    private val api : CryptoAPI
) {
    suspend fun getCryptoList() : Resource<CryptoList> {
        val response = try {
            api.getCryptoList()

        } catch (e: Exception) {
            return Resource.Error("Error!")
        }
        return Resource.Success(response)
    }

    suspend fun getCrypto(id: String) : Resource<Crypto> {
        val response = try {
            api.getCrypto()
        } catch (e: Exception) {
            return Resource.Error("Error!")
        }
        return Resource.Success(response)
    }
}