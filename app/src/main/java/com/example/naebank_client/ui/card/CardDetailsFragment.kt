package com.example.naebank_client.ui.card

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import com.example.naebank_client.databinding.FragmentCardDetailsBinding
import com.example.naebank_client.ui.AddActivity
import com.example.naebank_client.ui.BaseFragment

class CardDetailsFragment : BaseFragment() {
  private lateinit var binding: FragmentCardDetailsBinding
  private var cardId: Long? = null
  private var userId: Long? = null
  private var balance: Int = 0
  private var cardName: String? = null

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    binding = FragmentCardDetailsBinding.inflate(inflater, container, false)

    cardId = arguments?.getLong(CARD_ID) ?: 0
    userId = prefs.getLong(USER_ID, 0)

    vm.onCardResult.observe(viewLifecycleOwner) { card ->
      binding.cardTypeValue.text = card.type
      binding.cardMaskValue.text = card.mask
      binding.cardExpMonthValue.text = card.expMonth.toString()
      binding.cardExpYearValue.text = card.expYear.toString()
      binding.cardBalanceValue.text = card.amount.toString() + " UAH"
      balance = card.amount

      cardName = card.type + " " + card.mask
    }

    vm.onCardDeleted.observe(viewLifecycleOwner) {
      Toast.makeText(requireActivity(), "Card deleted", Toast.LENGTH_SHORT).show()
      requireActivity().finish()
    }

    vm.onError.observe(viewLifecycleOwner) {
      Log.d("denys", it)
    }

    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    userId = prefs.getLong(USER_ID, 0)

    binding.cardDeleteBtn.setOnClickListener {
      vm.deleteCard(
        id = cardId!!
      )
    }

    binding.cardWithdrawBtn.setOnClickListener {
      (requireActivity() as AddActivity).topUpFragment(
        CardOperationsFragment.getInstance(
          cardId = cardId!!,
          cardName = cardName!!,
          operation = CardOperationsFragment.Companion.OPERATION.WITHDRAW, balance
        ), true
      )
    }

    binding.cardTopupBtn.setOnClickListener {
      (requireActivity() as AddActivity).topUpFragment(
        CardOperationsFragment.getInstance(
          cardId = cardId!!,
          cardName = cardName!!,
          operation = CardOperationsFragment.Companion.OPERATION.TOPUP, balance
        ), true
      )
    }

    binding.close.setOnClickListener {
      requireActivity().finish()
    }
  }

  override fun onResume() {
    super.onResume()
    vm.getCardById(userId ?: 0, cardId ?: 0)
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