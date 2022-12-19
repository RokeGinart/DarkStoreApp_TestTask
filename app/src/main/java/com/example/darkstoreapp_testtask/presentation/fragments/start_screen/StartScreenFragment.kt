package com.example.darkstoreapp_testtask.presentation.fragments.start_screen

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.darkstoreapp_testtask.R
import com.example.darkstoreapp_testtask.data.di.CustomViewModelFactory
import com.example.darkstoreapp_testtask.databinding.FragmentStartScreenBinding
import com.example.darkstoreapp_testtask.domain.callbacks.OnDatePickerListener
import com.example.darkstoreapp_testtask.domain.model.ui.DateAction
import com.example.darkstoreapp_testtask.domain.model.ui.DateModel
import com.example.darkstoreapp_testtask.domain.model.ui.start_screen.ExchangeRateUiModel
import com.example.darkstoreapp_testtask.presentation.activities.MainActivity
import com.example.darkstoreapp_testtask.presentation.dialogs.DatePicker
import com.example.darkstoreapp_testtask.utils.InternetConnectionManager
import com.example.darkstoreapp_testtask.utils.KeyboardUtils
import com.example.darkstoreapp_testtask.utils.toStringVersion
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject


class StartScreenFragment : Fragment(), OnDatePickerListener {

    private lateinit var binding: FragmentStartScreenBinding

    @Inject
    lateinit var viewModelFactory: CustomViewModelFactory

    private val viewModel: StartScreenViewModel by viewModels { viewModelFactory }

    private val dateDialog = DatePicker()
    private var dateModel: DateModel? = null
    private val calendar = Calendar.getInstance()
    private var isSelectedDateCorrect = true

    private lateinit var toCurrencyAdapter: ArrayAdapter<ExchangeRateUiModel>

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context as MainActivity).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initDialog()

        initViews()

        observe()

        viewModel.setCurrentDate(
            DateModel(
                year = calendar.get(Calendar.YEAR),
                month = calendar.get(Calendar.MONTH),
                day = calendar.get(Calendar.DAY_OF_MONTH)
            )
        )

        viewModel.checkDate(dateModel)
    }

    private fun initDialog() {
        dateDialog.setParentFragmentListener(this)
        dateDialog.isCancelable = false
    }

    private fun initViews() {
        with(binding) {
            calendarIv.setOnClickListener {
                dateDialog.setDate(dateModel)
                dateDialog.show(childFragmentManager, "DatePickerDialog")
                KeyboardUtils.closeKeyboard(requireContext(), calendarIv)
            }

            historyIv.setOnClickListener {
                viewModel.navigateToHistoryScreen()
                KeyboardUtils.closeKeyboard(requireContext(), historyIv)
            }

            buttonConvert.setOnClickListener {
                if (!amountEt.text.isNullOrEmpty()) {
                    viewModel.convertValue(
                        amountEt.text.toString(),
                        Calendar.getInstance().timeInMillis,
                        toCurrencySpinner.selectedItem as ExchangeRateUiModel
                    )
                } else {
                    showMessage(getString(R.string.enter_amount_error))
                }
                KeyboardUtils.closeKeyboard(requireContext(), buttonConvert)
            }

            amountEt.doAfterTextChanged {
                enableToConvert(isSelectedDateCorrect)
            }
        }
    }

    private fun initSpinners() {
        with(binding) {
            toCurrencySpinner.adapter = toCurrencyAdapter
        }
    }

    private fun initSpinnerAdapter(list: List<ExchangeRateUiModel>) {
        toCurrencyAdapter = ArrayAdapter(
            requireContext(),
            R.layout.spinner_item,
            list
        )

        initSpinners()
    }

    private fun enableToConvert(isEnabled: Boolean) = with(binding) {
        toCurrencySpinner.isEnabled = isEnabled
        buttonConvert.isEnabled = isEnabled
        binding.messageIm.isVisible = !isEnabled
    }

    private fun observe() {
        with(viewLifecycleOwner.lifecycleScope) {
            launch { observeCurrentRate() }
            launch { observeDate() }
            launch { observeErrors() }
            launch { observeTotalAmount() }
        }
    }

    private suspend fun observeCurrentRate() {
        viewModel.currencyList.collect {
            initSpinnerAdapter(it)
            enableToConvert(true)
        }
    }

    private suspend fun observeTotalAmount() {
        viewModel.totalAmount.collect {
            binding.totalResultTv.text = getString(R.string.total_result, it)
        }
    }

    private suspend fun observeDate() {
        viewModel.dateActions.collect {
            when (it) {
                DateAction.DATE_IS_EMPTY -> dateDialog.show(
                    childFragmentManager,
                    "DatePickerDialog"
                )
                DateAction.WRONG_DATE -> showMessage(getString(it.error))
            }

            isSelectedDateCorrect = false
        }
    }

    private suspend fun observeErrors() {
        viewModel.errors.collect {
            if (!InternetConnectionManager.isNetworkAvailable(requireContext())) {
                showMessage(getString(R.string.no_internet_connection_message))
            } else {
                showMessage(getString(it.error))
            }
        }
    }

    override fun dateSelected(date: DateModel) {
        isSelectedDateCorrect = true
        dateModel = date
        viewModel.checkDate(date)
        binding.selectedDateTv.text = date.toStringVersion()
    }

    private fun showMessage(text: String) = with(binding) {
        messageIm.isVisible = true
        messageIm.showMessage(text, true)
        enableToConvert(false)
        KeyboardUtils.closeKeyboard(requireContext(), messageIm)
    }
}