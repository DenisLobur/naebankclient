package com.example.naebank_client.ui.card

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.naebank_client.databinding.FragmentAddCardBinding
import com.example.naebank_client.ui.BaseFragment

class AddCardFragment : BaseFragment() {
  private lateinit var binding: FragmentAddCardBinding

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    binding = FragmentAddCardBinding.inflate(inflater, container, false)

    vm.onCardAdded.observe(viewLifecycleOwner) {
      Log.d("denys", "card added!")
      requireActivity().finish()
    }

    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.cardAddBtn.setOnClickListener {
      Log.d("denys", "adding card...")
      vm.addCard(
        type = binding.cardType.text.toString(),
        mask = binding.cardMask.text.toString(),
        expMonth = binding.cardExpMonth.text.toString().toInt(),
        expYear = binding.cardExpYear.text.toString().toInt(),
        isDefault = false
      )
    }

    binding.close.setOnClickListener {
      requireActivity().finish()
    }
  }
}