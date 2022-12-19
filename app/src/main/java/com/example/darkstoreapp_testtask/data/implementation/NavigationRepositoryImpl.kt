package com.example.darkstoreapp_testtask.data.implementation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.darkstoreapp_testtask.R
import com.example.darkstoreapp_testtask.domain.repositories.NavigationRepository
import com.example.darkstoreapp_testtask.presentation.fragments.history_screen.HistoryFragment
import com.example.darkstoreapp_testtask.presentation.fragments.start_screen.StartScreenFragment
import javax.inject.Inject


class NavigationRepositoryImpl @Inject constructor() : NavigationRepository {

    override var fragmentManager: FragmentManager? = null

    override fun attachFragmentManager(fragmentManager: FragmentManager) {
        this.fragmentManager = fragmentManager
    }

    override fun detachFragmentManager() {
        fragmentManager = null
    }

    override fun navigateToStartScreen() {
        navigateTo(StartScreenFragment(), addToBackStack = false, isAnimNecessary = false)
    }

    override fun navigateToHistoryScreen() {
        navigateTo(HistoryFragment(), addToBackStack = true, isAnimNecessary = true)
    }

    private fun navigateTo(
        fragment: Fragment,
        addToBackStack: Boolean = true,
        type: NavType = NavType.ADD,
        isAnimNecessary: Boolean = true
    ) {
        fragmentManager?.let {
            val transaction: FragmentTransaction = it.beginTransaction()

            if(isAnimNecessary) {
                transaction.setCustomAnimations(
                    R.anim.fm_anim_enter, R.anim.fm_anim_exit,
                    R.anim.fm_anim_pop_enter, R.anim.fm_anim_pop_exit
                )
            }

            when (type) {
                NavType.ADD -> transaction.add(R.id.container, fragment)
                NavType.REPLACE -> transaction.replace(R.id.container, fragment)
            }

            if(addToBackStack) transaction.addToBackStack(fragment.tag)
            transaction.commit()
        }
    }

    internal enum class NavType {
        ADD, REPLACE
    }
}