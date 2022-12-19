package com.example.darkstoreapp_testtask.data.di.modules

import com.example.darkstoreapp_testtask.data.implementation.ApiRepositoryImpl
import com.example.darkstoreapp_testtask.data.implementation.ExchangeHistoryRepositoryImpl
import com.example.darkstoreapp_testtask.domain.repositories.ApiRepository
import com.example.darkstoreapp_testtask.domain.repositories.ExchangeHistoryRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindApiService(apiRepositoryImpl: ApiRepositoryImpl): ApiRepository

    @Binds
    abstract fun bindExchangeHistoryRepository(exchangeHistoryRepositoryImpl: ExchangeHistoryRepositoryImpl): ExchangeHistoryRepository
}