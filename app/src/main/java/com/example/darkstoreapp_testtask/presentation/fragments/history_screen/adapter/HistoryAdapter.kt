package com.example.darkstoreapp_testtask.presentation.fragments.history_screen.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.darkstoreapp_testtask.R
import com.example.darkstoreapp_testtask.databinding.HistoryItemBinding
import com.example.darkstoreapp_testtask.domain.model.db.ExchangeHistoryItemEntity

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.HistoryItemViewHolder>() {

    private val historyList = arrayListOf<ExchangeHistoryItemEntity>()

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<ExchangeHistoryItemEntity>) {
        historyList.clear()
        historyList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryItemViewHolder {
        val view = HistoryItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
       return HistoryItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryItemViewHolder, position: Int) {
        val item = historyList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = historyList.size

    class HistoryItemViewHolder(private val binding: HistoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ExchangeHistoryItemEntity) {
            with(binding) {
               dateTv.text = item.date
               exchangeRateTv.text = root.context.getString(
                    R.string.exchange_rate_value,
                    "${String.format("%.2f", item.exchangeRate)} ${item.toCurrency}"
                )
                userAmountTv.text = root.context.getString(
                    R.string.uah_amount,
                    item.fromSum
                )
                val total = "${item.totalSum} ${item.toCurrency}"
                resultAmountTv.text = total
            }
        }
    }
}