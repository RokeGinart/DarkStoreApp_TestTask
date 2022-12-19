package com.example.darkstoreapp_testtask.data.implementation

import com.example.darkstoreapp_testtask.data.db.ExchangeHistoryDao
import com.example.darkstoreapp_testtask.domain.model.db.ExchangeHistoryItemEntity
import com.example.darkstoreapp_testtask.domain.repositories.ExchangeHistoryRepository
import javax.inject.Inject

class ExchangeHistoryRepositoryImpl @Inject constructor(
    private val dao: ExchangeHistoryDao
) : ExchangeHistoryRepository {

    override suspend fun getExchangeHistoryItemEntity(): List<ExchangeHistoryItemEntity> = dao.getExchangeHistoryItemEntity()
    override suspend fun insertExchangeHistoryItemEntity(exchangeHistoryItemEntity: ExchangeHistoryItemEntity) = dao.insertExchangeHistoryItemEntity(exchangeHistoryItemEntity)
}