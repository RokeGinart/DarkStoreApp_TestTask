package com.example.darkstoreapp_testtask.domain.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ExchangeHistoryItemEntity(
    @PrimaryKey
    val id: Long,
    val units: Int,
    val date: String,
    val toCurrency: String,
    val exchangeRate: Double,
    val fromSum: String,
    val totalSum: String,
)