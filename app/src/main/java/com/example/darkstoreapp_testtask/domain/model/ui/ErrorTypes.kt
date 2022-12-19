package com.example.darkstoreapp_testtask.domain.model.ui

import androidx.annotation.StringRes
import com.example.darkstoreapp_testtask.R

enum class ErrorTypes(@StringRes val error : Int) {
    SMTH_WENT_WRONG(R.string.smth_went_wrong),
    EMPTY_DATA(R.string.empty_body),
}