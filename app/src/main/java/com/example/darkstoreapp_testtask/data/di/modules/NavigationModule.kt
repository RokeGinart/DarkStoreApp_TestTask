package com.example.darkstoreapp_testtask.data.di.modules

import com.example.darkstoreapp_testtask.data.implementation.NavigationRepositoryImpl
import com.example.darkstoreapp_testtask.domain.repositories.NavigationRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface NavigationModule {

    @Binds
    @Singleton
    fun bindNavigationRepositoryImpl(navigationRepositoryImpl: NavigationRepositoryImpl): NavigationRepository
}