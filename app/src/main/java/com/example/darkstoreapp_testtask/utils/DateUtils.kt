package com.example.darkstoreapp_testtask.utils

import com.example.darkstoreapp_testtask.domain.model.ui.DateModel

fun DateModel.toStringVersion() : String = "${this.day}.${this.month+1}.${this.year}"