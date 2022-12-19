package com.example.darkstoreapp_testtask.presentation.fragments.history_screen

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.darkstoreapp_testtask.data.di.CustomViewModelFactory
import com.example.darkstoreapp_testtask.databinding.HistoryFragmentBinding
import com.example.darkstoreapp_testtask.presentation.activities.MainActivity
import com.example.darkstoreapp_testtask.presentation.fragments.history_screen.adapter.HistoryAdapter
import kotlinx.coroutines.launch
import javax.inject.Inject

class HistoryFragment : Fragment() {


    @Inject
    lateinit var viewModelFactory: CustomViewModelFactory

    private val viewModel: HistoryViewModel by viewModels { viewModelFactory }

    private lateinit var binding : HistoryFragmentBinding

    private val historyAdapter = HistoryAdapter()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context as MainActivity).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HistoryFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()

        observe()

        viewModel.getHistory()
    }

    private fun initViews(){
        with(binding){
            backButtonIv.setOnClickListener {
                requireActivity().onBackPressed()
            }

            val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            historyRv.apply {
                this.layoutManager = layoutManager
                this.adapter = historyAdapter
            }
        }
    }

    private fun observe() {
        with(viewLifecycleOwner.lifecycleScope) {
            launch { observeHistory() }
            launch { observeEmptyStatus() }
        }
    }

    private suspend fun observeHistory() {
        viewModel.historyList.collect {
            historyAdapter.setList(it)
            binding.noDataContainer.isVisible = false
        }
    }

    private suspend fun observeEmptyStatus() {
        viewModel.emptyStatus.collect {
            binding.noDataContainer.isVisible = true
        }
    }
}