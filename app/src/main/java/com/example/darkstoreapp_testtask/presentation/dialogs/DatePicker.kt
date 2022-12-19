package com.example.darkstoreapp_testtask.presentation.dialogs

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.darkstoreapp_testtask.domain.callbacks.OnDatePickerListener
import com.example.darkstoreapp_testtask.domain.model.ui.DateModel
import java.util.*

class DatePicker : DialogFragment(), DatePickerDialog.OnDateSetListener {
    private lateinit var onDatePickerListener : OnDatePickerListener
    private var date: DateModel? = null

    fun setParentFragmentListener(onDatePickerListener : OnDatePickerListener){
        this.onDatePickerListener = onDatePickerListener
    }

    fun setDate(date: DateModel?){
        this.date = date
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val mCalendar: Calendar = Calendar.getInstance()
        var year = mCalendar.get(Calendar.YEAR)
        var month = mCalendar.get(Calendar.MONTH)
        var day = mCalendar.get(Calendar.DAY_OF_MONTH)

        date?.let {
            year = it.year
            month = it.month
            day = it.day
        }

        return DatePickerDialog(requireContext(), this, year, month, day)
    }

    override fun onDateSet(
        view: android.widget.DatePicker?,
        year: Int,
        month: Int,
        dayOfMonth: Int
    ) {
        onDatePickerListener.dateSelected(DateModel(year, month, dayOfMonth))
    }
}