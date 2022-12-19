package com.example.darkstoreapp_testtask.domain.model.ui

import androidx.annotation.StringRes
import com.example.darkstoreapp_testtask.R

enum class DateAction(@StringRes val error : Int) {
    DATE_IS_EMPTY(R.string.smth_went_wrong),
    WRONG_DATE(R.string.wrong_date),
}