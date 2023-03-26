package com.example.naebank_client.ui.card

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.naebank_client.data.Data
import com.example.naebank_client.databinding.ItemCardTransactionBinding
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CardsTransactionsAdapter() : RecyclerView.Adapter<CardsTransactionsAdapter.Holder>() {

  private val data: MutableList<Data.TransactionResponse> = mutableListOf()

  fun setData(list: List<Data.TransactionResponse>) {
    if (data.isNotEmpty()) {
      data.clear()
    }

    data.addAll(list)
  }

  class Holder(val binding: ItemCardTransactionBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(trans: Data.TransactionResponse) {
      binding.transactionType.text = trans.type
      binding.transactionAmount.text = if (trans.type.equals("withdraw", ignoreCase = true)) "-${trans.amount} UAH" else "+${trans.amount} UAH"
      binding.transactionDate.text = DATE_FORMAT.format(Date(trans.date ?: 0))

    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
    return Holder(ItemCardTransactionBinding.inflate(LayoutInflater.from(parent.context), parent, false))
  }

  override fun getItemCount(): Int = data.size

  override fun onBindViewHolder(holder: Holder, position: Int) {
    holder.bind(data[position])
  }

  companion object {
    val DATE_FORMAT: DateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
  }
}