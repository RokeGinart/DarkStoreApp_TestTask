package com.example.darkstoreapp_testtask.data.network

import com.example.darkstoreapp_testtask.domain.model.responses.ExchangeRateResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("exchange?json")
    suspend fun getExchangeRateList(
        @Query("date") date : String
    ): Response<ExchangeRateResponse>
}