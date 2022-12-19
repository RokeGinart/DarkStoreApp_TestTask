package com.example.darkstoreapp_testtask.data.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.darkstoreapp_testtask.data.di.CustomViewModelFactory
import com.example.darkstoreapp_testtask.data.di.ViewModelKey
import com.example.darkstoreapp_testtask.presentation.activities.MainViewModel
import com.example.darkstoreapp_testtask.presentation.fragments.history_screen.HistoryViewModel
import com.example.darkstoreapp_testtask.presentation.fragments.start_screen.StartScreenViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: CustomViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    protected abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(StartScreenViewModel::class)
    protected abstract fun bindStartScreenViewModel(startScreenViewModel: StartScreenViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HistoryViewModel::class)
    protected abstract fun bindHistoryViewModel(historyViewModel: HistoryViewModel): ViewModel
}