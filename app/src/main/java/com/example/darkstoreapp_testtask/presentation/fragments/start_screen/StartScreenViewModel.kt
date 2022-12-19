package com.example.darkstoreapp_testtask.presentation.fragments.start_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.darkstoreapp_testtask.domain.model.db.ExchangeHistoryItemEntity
import com.example.darkstoreapp_testtask.domain.model.responses.ExchangeRateResponseItem
import com.example.darkstoreapp_testtask.domain.model.ui.DateAction
import com.example.darkstoreapp_testtask.domain.model.ui.DateModel
import com.example.darkstoreapp_testtask.domain.model.ui.ErrorTypes
import com.example.darkstoreapp_testtask.domain.model.ui.start_screen.ExchangeRateUiModel
import com.example.darkstoreapp_testtask.domain.repositories.NavigationRepository
import com.example.darkstoreapp_testtask.domain.usecases.GetExchangeHistoryDBUseCase
import com.example.darkstoreapp_testtask.domain.usecases.GetExchangeRateNetworkUseCase
import com.example.darkstoreapp_testtask.domain.usecases.SetExchangeHistoryItemDBUseCase
import com.example.darkstoreapp_testtask.utils.toStringVersion
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class StartScreenViewModel @Inject constructor(
    private val getExchangeRateNetworkUseCase: GetExchangeRateNetworkUseCase,
    private val navigation: NavigationRepository,
    private val setExchangeHistoryItemDBUseCase: SetExchangeHistoryItemDBUseCase,
) : ViewModel() {

    val errors = MutableSharedFlow<ErrorTypes>()
    val dateActions = MutableSharedFlow<DateAction>()
    val currencyList = MutableSharedFlow<List<ExchangeRateUiModel>>()
    val totalAmount = MutableSharedFlow<String>()
    private lateinit var currentDate : DateModel
    private lateinit var selectedDate : String

    fun setCurrentDate(date : DateModel){
        currentDate = date
    }

    fun checkDate(date: DateModel?) {
        viewModelScope.launch {
            if (date == null) {
                dateActions.emit(DateAction.DATE_IS_EMPTY)
            } else {
                if(date.year > currentDate.year){
                    dateActions.emit(DateAction.WRONG_DATE)
                } else if(date.month > currentDate.month){
                    dateActions.emit(DateAction.WRONG_DATE)
                } else if(date.day > currentDate.day){
                    dateActions.emit(DateAction.WRONG_DATE)
                } else {
                    selectedDate = date.toStringVersion()
                    getCurrencyRateForSelectedDate()
                }
            }
        }
    }

    private fun getCurrencyRateForSelectedDate() {
        viewModelScope.launch {
            try {
                val result = getExchangeRateNetworkUseCase.execute(selectedDate)
                if (result.isSuccessful) {
                    result.body()?.let { body ->
                        val exchangeRateList = arrayListOf<ExchangeRateUiModel>()

                        body.forEach {
                            exchangeRateList.add(mapResponseToExchangeRateUiModel(it))
                        }

                        currencyList.emit(exchangeRateList)
                    } ?: kotlin.run {
                        errors.emit(ErrorTypes.EMPTY_DATA)
                    }
                } else {
                    errors.emit(ErrorTypes.SMTH_WENT_WRONG)
                }
            } catch (exception: Exception) {
                errors.emit(ErrorTypes.SMTH_WENT_WRONG)
            }
        }
    }

    fun convertValue(userAmount : String, id: Long, selectedCurrency :ExchangeRateUiModel){
        viewModelScope.launch {
            val total = userAmount.toDouble() * selectedCurrency.amount
            val totalInStringFormat = String.format("%.2f", total)
            totalAmount.emit("$totalInStringFormat ${selectedCurrency.currencyName}")

            setExchangeHistoryItemDBUseCase.execute(ExchangeHistoryItemEntity(
                id = id,
                date = selectedDate,
                toCurrency = selectedCurrency.currencyName,
                exchangeRate = selectedCurrency.amount,
                fromSum = userAmount,
                totalSum = totalInStringFormat
            ))
        }
    }

    private fun mapResponseToExchangeRateUiModel(item: ExchangeRateResponseItem): ExchangeRateUiModel =
        ExchangeRateUiModel(
            date = item.StartDate,
            amount = item.Amount,
            currencyName = item.CurrencyCodeL
        )

    fun navigateToHistoryScreen(){
        navigation.navigateToHistoryScreen()
    }
}