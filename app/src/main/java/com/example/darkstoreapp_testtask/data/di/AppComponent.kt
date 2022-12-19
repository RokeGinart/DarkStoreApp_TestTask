package com.example.darkstoreapp_testtask.data.di

import android.app.Application
import android.content.Context
import com.example.darkstoreapp_testtask.data.di.modules.*
import com.example.darkstoreapp_testtask.presentation.activities.MainActivity
import com.example.darkstoreapp_testtask.presentation.fragments.history_screen.HistoryFragment
import com.example.darkstoreapp_testtask.presentation.fragments.start_screen.StartScreenFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    NetworkModule::class,
    DBModule::class,
    NavigationModule::class,
    RepositoryModule::class,
    ViewModelModule::class
])
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(fragment: StartScreenFragment)
    fun inject(fragment: HistoryFragment)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(app: Context) : Builder

        fun build(): AppComponent
    }
}