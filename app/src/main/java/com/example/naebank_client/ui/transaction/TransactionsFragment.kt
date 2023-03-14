package com.example.naebank_client.ui.transaction

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.naebank_client.databinding.FragmentTransactionsBinding
import com.example.naebank_client.ui.BaseFragment

class TransactionsFragment : BaseFragment() {

  private lateinit var binding: FragmentTransactionsBinding

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    binding = FragmentTransactionsBinding.inflate(inflater, container, false)

    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    Log.d("denys", "in transa fragment")
  }
}