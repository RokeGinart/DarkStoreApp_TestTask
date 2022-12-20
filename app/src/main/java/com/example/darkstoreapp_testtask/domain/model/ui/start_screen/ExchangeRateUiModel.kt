package com.example.darkstoreapp_testtask.domain.model.ui.start_screen

data class ExchangeRateUiModel(
    val date : String,
    val amount: Double,
    val units: Int,
    val currencyName : String
) {
    override fun toString(): String {
        return currencyName
    }
}
