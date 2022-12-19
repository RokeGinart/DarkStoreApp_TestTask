package com.example.darkstoreapp_testtask.presentation.fragments.history_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.darkstoreapp_testtask.domain.model.db.ExchangeHistoryItemEntity
import com.example.darkstoreapp_testtask.domain.usecases.GetExchangeHistoryDBUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class HistoryViewModel @Inject constructor(
     private val getExchangeHistoryDBUseCase: GetExchangeHistoryDBUseCase
) : ViewModel() {

    val historyList = MutableSharedFlow<List<ExchangeHistoryItemEntity>>()
    val emptyStatus = MutableSharedFlow<Unit>()

    fun getHistory() {
        viewModelScope.launch {
            val result = getExchangeHistoryDBUseCase.execute()
            if (result.isNotEmpty()) {
                historyList.emit(result)
            } else {
                emptyStatus.emit(Unit)
            }
        }
    }
}