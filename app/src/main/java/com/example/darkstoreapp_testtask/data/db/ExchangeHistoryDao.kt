package com.example.darkstoreapp_testtask.data.db

import androidx.room.*
import com.example.darkstoreapp_testtask.domain.model.db.ExchangeHistoryItemEntity

@Dao
interface ExchangeHistoryDao {

    @Query("SELECT * FROM ExchangeHistoryItemEntity ORDER BY id DESC LIMIT 10")
    suspend fun getExchangeHistoryItemEntity() : List<ExchangeHistoryItemEntity>

    @Insert(entity = ExchangeHistoryItemEntity::class)
    suspend fun insertExchangeHistoryItemEntity(exchangeHistoryItemEntity: ExchangeHistoryItemEntity)

    @Query("DELETE FROM ExchangeHistoryItemEntity")
    suspend fun deleteExchangeHistoryItemEntity() : Int?

    @Transaction
    suspend fun clearTableAndInsert(exchangeHistoryItemEntity: ExchangeHistoryItemEntity){
        deleteExchangeHistoryItemEntity()
        insertNewExchangeHistoryItemEntity(exchangeHistoryItemEntity)
    }

    suspend fun insertNewExchangeHistoryItemEntity(exchangeHistoryItemEntity: ExchangeHistoryItemEntity){
        clearTableAndInsert(exchangeHistoryItemEntity)
    }
}