package com.example.darkstoreapp_testtask.domain.usecases

import com.example.darkstoreapp_testtask.domain.model.db.ExchangeHistoryItemEntity
import com.example.darkstoreapp_testtask.domain.repositories.ExchangeHistoryRepository
import javax.inject.Inject

class SetExchangeHistoryItemDBUseCase @Inject constructor(private val exchangeHistoryRepository: ExchangeHistoryRepository) {
    suspend fun execute(exchangeHistoryItemEntity: ExchangeHistoryItemEntity) =
        exchangeHistoryRepository.insertExchangeHistoryItemEntity(exchangeHistoryItemEntity)
}