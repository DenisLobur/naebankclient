package com.example.naebank_client.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import com.example.naebank_client.databinding.FragmentCardDetailsBinding

class CardDetailsFragment : BaseFragment() {
  private lateinit var binding: FragmentCardDetailsBinding
  private var cardId: Long? = null
  private var userId: Long? = null

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    binding = FragmentCardDetailsBinding.inflate(inflater, container, false)

    cardId = arguments?.getLong(CARD_ID) ?: 0
    userId = prefs.getLong(USER_ID, 0)

    vm.getCardById(userId ?: 0, cardId ?: 0)

    vm.onCardResult.observe(viewLifecycleOwner) { card ->
      binding.cardTypeValue.text = card.type
      binding.cardMaskValue.text = card.mask
      binding.cardExpMonthValue.text = card.expMonth.toString()
      binding.cardExpYearValue.text = card.expYear.toString()
      binding.cardBalanceValue.text = card.amount.toString() + " UAH"
    }

    vm.onCardDeleted.observe(viewLifecycleOwner) {
      Toast.makeText(requireActivity(), "Card deleted", Toast.LENGTH_SHORT).show()
      requireActivity().finish()
    }

    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    userId = prefs.getLong(USER_ID, 0)

    binding.cardDeleteBtn.setOnClickListener {
      Log.d("denys", "cardId $id")
//      vm.deleteCard(
//        id = id!!
//      )
    }

    binding.close.setOnClickListener {
      requireActivity().finish()
    }
  }

  companion object {
    const val CARD_ID = "card_id"

    fun getInstance(cardId: Long): CardDetailsFragment {
      val fragment = CardDetailsFragment()
      fragment.arguments = bundleOf(
        CARD_ID to cardId
      )

      return fragment
    }
  }
}