package com.smtersoyoglu.composecryptoapp.service

import com.smtersoyoglu.composecryptoapp.model.Crypto
import com.smtersoyoglu.composecryptoapp.model.CryptoList
import retrofit2.http.GET

interface CryptoAPI {

    @GET("cryptolist.json")
    suspend fun getCryptoList(): CryptoList

    @GET("crypto.json")
    suspend fun getCrypto(): Crypto


}