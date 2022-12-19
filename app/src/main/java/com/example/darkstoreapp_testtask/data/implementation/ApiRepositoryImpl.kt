package com.example.darkstoreapp_testtask.data.implementation

import com.example.darkstoreapp_testtask.data.network.ApiService
import com.example.darkstoreapp_testtask.domain.model.responses.ExchangeRateResponse
import com.example.darkstoreapp_testtask.domain.repositories.ApiRepository
import retrofit2.Response
import javax.inject.Inject

class ApiRepositoryImpl  @Inject constructor(
    private val apiService: ApiService
) : ApiRepository {

    override suspend fun getExchangeRateList(date: String): Response<ExchangeRateResponse> =
        apiService.getExchangeRateList(date)
}