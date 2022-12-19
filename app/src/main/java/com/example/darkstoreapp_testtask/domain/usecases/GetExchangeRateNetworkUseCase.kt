package com.example.darkstoreapp_testtask.domain.usecases

import com.example.darkstoreapp_testtask.domain.model.responses.ExchangeRateResponse
import com.example.darkstoreapp_testtask.domain.repositories.ApiRepository
import retrofit2.Response
import javax.inject.Inject

class GetExchangeRateNetworkUseCase @Inject constructor(private val apiRepository: ApiRepository) {
    suspend fun execute(date: String): Response<ExchangeRateResponse> =
        apiRepository.getExchangeRateList(date)
}
