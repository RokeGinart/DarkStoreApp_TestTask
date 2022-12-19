package com.example.darkstoreapp_testtask.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.darkstoreapp_testtask.domain.model.db.ExchangeHistoryItemEntity

@Database(entities = [ExchangeHistoryItemEntity::class], version = 1, exportSchema = false)
abstract class ExchangeHistoryDatabase : RoomDatabase(){
    abstract fun getExchangeHistoryDao(): ExchangeHistoryDao
}