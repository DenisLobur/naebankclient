package com.example.naebank_client.ui.transaction

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.naebank_client.databinding.FragmentTransactionsBinding
import com.example.naebank_client.ui.AddActivity
import com.example.naebank_client.ui.BaseFragment
import com.example.naebank_client.ui.card.CardListAdapter

class TransactionsFragment : BaseFragment() {

  private lateinit var binding: FragmentTransactionsBinding

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    binding = FragmentTransactionsBinding.inflate(inflater, container, false)

    vm.onTransactionsResult.observe(viewLifecycleOwner) {
      binding.hasTransactions = it.isNotEmpty()

      val adapter = TransactionListAdapter(object : TransactionListAdapter.OnTransactionClick {
        override fun onTransactionClick(id: Long) {
          //TODO: add transaction details if needed
        }
      })
      adapter.setData(it)
      binding.transactionsRv.adapter = adapter
    }
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    Log.d("denys", "in transa fragment")
  }

  override fun onResume() {
    super.onResume()
    val userId = prefs.getLong(USER_ID, 0)
    vm.getTransactionsByUserId(userId)
  }
}