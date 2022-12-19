package com.example.darkstoreapp_testtask.data.di.modules

import android.content.Context
import androidx.room.Room
import com.example.darkstoreapp_testtask.App
import com.example.darkstoreapp_testtask.data.db.ExchangeHistoryDao
import com.example.darkstoreapp_testtask.data.db.ExchangeHistoryDatabase
import dagger.Module
import dagger.Provides

@Module
class DBModule {

    @Provides
    fun provideExchangeHistoryDatabase(context: Context): ExchangeHistoryDatabase = Room.databaseBuilder(
            context, ExchangeHistoryDatabase::class.java,"ExchangeHistoryDatabase").build()


    @Provides
    fun provideAccessTokenDao(exchangeHistoryDatabase: ExchangeHistoryDatabase): ExchangeHistoryDao {
        return exchangeHistoryDatabase.getExchangeHistoryDao()
    }
}