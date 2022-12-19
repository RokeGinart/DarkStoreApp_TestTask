package com.example.darkstoreapp_testtask.domain.callbacks

import com.example.darkstoreapp_testtask.domain.model.ui.DateModel

interface OnDatePickerListener {
    fun dateSelected(date : DateModel)
}