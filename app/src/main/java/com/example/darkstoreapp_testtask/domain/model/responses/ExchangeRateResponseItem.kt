package com.example.darkstoreapp_testtask.domain.model.responses

data class ExchangeRateResponseItem(
    val Amount: Double,
    val CurrencyCode: String,
    val CurrencyCodeL: String,
    val StartDate: String,
    val TimeSign: String,
    val Units: Int
)