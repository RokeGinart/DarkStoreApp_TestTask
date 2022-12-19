package com.example.darkstoreapp_testtask.presentation.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.darkstoreapp_testtask.App
import com.example.darkstoreapp_testtask.R
import com.example.darkstoreapp_testtask.data.di.AppComponent
import com.example.darkstoreapp_testtask.data.di.CustomViewModelFactory
import javax.inject.Inject

class MainActivity @Inject constructor() : AppCompatActivity() {

    lateinit var appComponent : AppComponent

    @Inject
    lateinit var viewModelFactory: CustomViewModelFactory

    private val viewModel: MainViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent = (applicationContext as App).appComponent
        appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.setFragmentManager(supportFragmentManager)
    }

    override fun onDestroy() {
        viewModel.detachFragmentManager()
        super.onDestroy()
    }
}