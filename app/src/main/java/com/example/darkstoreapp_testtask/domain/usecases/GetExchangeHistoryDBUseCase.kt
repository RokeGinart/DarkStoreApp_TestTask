package com.example.darkstoreapp_testtask.domain.usecases

import com.example.darkstoreapp_testtask.domain.model.db.ExchangeHistoryItemEntity
import com.example.darkstoreapp_testtask.domain.repositories.ExchangeHistoryRepository
import javax.inject.Inject

class GetExchangeHistoryDBUseCase @Inject constructor(private val exchangeHistoryRepository: ExchangeHistoryRepository) {
    suspend fun execute(): List<ExchangeHistoryItemEntity> =
        exchangeHistoryRepository.getExchangeHistoryItemEntity()
}