package com.example.naebank_client.ui.card

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.naebank_client.data.Data
import com.example.naebank_client.databinding.ItemCardBinding

class CardListAdapter(val click: OnCardClick) : RecyclerView.Adapter<CardListAdapter.Holder>() {

  private val data: MutableList<Data.CardResponse> = mutableListOf()

  interface OnCardClick {
    fun onCardClick(id: Long)
  }
  fun setData(list: List<Data.CardResponse>) {
    if (data.isNotEmpty()) {
      data.clear()
    }

    data.addAll(list)
  }

  class Holder(val binding: ItemCardBinding, val onClickListener: OnCardClick) : RecyclerView.ViewHolder(binding.root) {
    fun bind(card: Data.CardResponse) {
      binding.cardType.text = card.type
      binding.cardMask.text = "****${card.mask}"
      binding.cardExp.text = "Exp.: ${card.expMonth}/${card.expYear}"

      binding.contentHolder.setOnClickListener {
        onClickListener.onCardClick(card.id)
      }
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
    return Holder(ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false), click)
  }

  override fun getItemCount(): Int = data.size

  override fun onBindViewHolder(holder: Holder, position: Int) {
    holder.bind(data[position])
  }
}