package com.example.naebank_client.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.naebank_client.databinding.FragmentCardsBinding

class CardsFragment : BaseFragment() {

  private lateinit var binding: FragmentCardsBinding

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    binding = FragmentCardsBinding.inflate(inflater, container, false)

    vm.onCardsResult.observe(viewLifecycleOwner) {
      binding.hasCards = it.isNotEmpty()

      val adapter = ListAdapter(object : ListAdapter.OnCardClick {
        override fun onCardClick(id: Long) {
          val intent = AddActivity.getIntent(requireActivity(), AddActivity.Companion.SCREEN.CARD_DETAILS, cardId = id)
          startActivity(intent)
        }
      })
      adapter.setData(it)
      binding.cardsRv.adapter = adapter
    }

    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.addCardButton.setOnClickListener {
      val intent = AddActivity.getIntent(requireActivity(), AddActivity.Companion.SCREEN.ADD_CARD)
      startActivity(intent)
    }
  }

  override fun onResume() {
    super.onResume()
    val userId = prefs.getLong(USER_ID, 0)
    vm.getCardsByUserId(userId)
  }
}