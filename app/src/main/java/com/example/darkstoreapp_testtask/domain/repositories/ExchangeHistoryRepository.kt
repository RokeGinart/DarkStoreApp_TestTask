package com.example.darkstoreapp_testtask.domain.repositories

import com.example.darkstoreapp_testtask.domain.model.db.ExchangeHistoryItemEntity

interface ExchangeHistoryRepository {
    suspend fun getExchangeHistoryItemEntity() : List<ExchangeHistoryItemEntity>
    suspend fun insertExchangeHistoryItemEntity(exchangeHistoryItemEntity: ExchangeHistoryItemEntity)
}