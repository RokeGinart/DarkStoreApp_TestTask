package com.example.darkstoreapp_testtask.domain.repositories

import com.example.darkstoreapp_testtask.domain.model.responses.ExchangeRateResponse
import retrofit2.Response

interface ApiRepository {
    suspend fun getExchangeRateList(date : String): Response<ExchangeRateResponse>
}