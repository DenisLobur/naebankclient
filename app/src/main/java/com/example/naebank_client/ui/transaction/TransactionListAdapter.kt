package com.example.naebank_client.ui.transaction

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.naebank_client.data.Data
import com.example.naebank_client.databinding.ItemTransactionBinding
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TransactionListAdapter(val click: OnTransactionClick) : RecyclerView.Adapter<TransactionListAdapter.Holder>() {

  private val data: MutableList<Data.TransactionResponse> = mutableListOf()

  interface OnTransactionClick {
    fun onTransactionClick(id: Long)
  }

  fun setData(list: List<Data.TransactionResponse>) {
    if (data.isNotEmpty()) {
      data.clear()
    }

    data.addAll(list)
  }

  class Holder(val binding: ItemTransactionBinding, val onClickListener: OnTransactionClick) : RecyclerView.ViewHolder(binding.root) {
    fun bind(trans: Data.TransactionResponse) {
      binding.transactionType.text = trans.type
      binding.cardName.text = trans.cardName
      binding.transactionId.text = "ID.: ${trans.id}"
      binding.transactionAmount.text = "${trans.amount} UAH"
      binding.transactionStatus.text = trans.status
      binding.transactionDate.text = DATE_FORMAT.format(Date(trans.date ?: 0))

      binding.contentHolder.setOnClickListener {
        onClickListener.onTransactionClick(trans.id)
      }
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
    return Holder(ItemTransactionBinding.inflate(LayoutInflater.from(parent.context), parent, false), click)
  }

  override fun getItemCount(): Int = data.size

  override fun onBindViewHolder(holder: Holder, position: Int) {
    holder.bind(data[position])
  }

  companion object {
    val DATE_FORMAT: DateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
  }
}