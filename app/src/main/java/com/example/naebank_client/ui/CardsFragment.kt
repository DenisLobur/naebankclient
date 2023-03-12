package com.example.naebank_client.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.naebank_client.databinding.FragmentCardsBinding
import com.example.naebank_client.databinding.FragmentUserBinding

class CardsFragment : BaseFragment() {

  private lateinit var binding: FragmentCardsBinding

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    binding = FragmentCardsBinding.inflate(inflater, container, false)

    vm.onCardsResult.observe(viewLifecycleOwner) {
      binding.hasCards = it.isNotEmpty()
      val adapter = ListAdapter()
      adapter.setData(it)
      binding.cardsRv.adapter = adapter
    }

    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.addCardButton.setOnClickListener {
      val intent = Intent(requireActivity(), AddActivity::class.java)
      startActivity(intent)
    }
  }

  override fun onResume() {
    super.onResume()
    vm.getCards()
  }
}